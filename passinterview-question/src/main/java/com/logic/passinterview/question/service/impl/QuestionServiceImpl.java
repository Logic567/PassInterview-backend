package com.logic.passinterview.question.service.impl;

import com.logic.passinterview.common.to.es.QuestionEsModel;
import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.common.utils.Query;
import com.logic.passinterview.common.utils.R;
import com.logic.passinterview.question.entity.TypeEntity;
import com.logic.passinterview.question.feign.SearchFeignService;
import com.logic.passinterview.question.service.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.logic.passinterview.question.dao.QuestionDao;
import com.logic.passinterview.question.entity.QuestionEntity;
import com.logic.passinterview.question.service.QuestionService;


@Service("questionService")
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, QuestionEntity> implements QuestionService {

    @Autowired
    TypeService typeService;

    @Autowired
    SearchFeignService searchFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        IPage<QuestionEntity> page = this.page(
//                new Query<QuestionEntity>().getPage(params),
//                new QueryWrapper<QuestionEntity>()
//        );
//
//        return new PageUtils(page);
        //1.get key
        String key = (String) params.get("key");
        QueryWrapper<QuestionEntity> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)){
            queryWrapper.eq("id",key).or().like("title",key).or().like("answer",key);
        }
        IPage<QuestionEntity> page = this.page(
                new Query<QuestionEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public boolean saveQuestion(QuestionEntity question) {
        boolean saveResult = save(question);
        saveEs(question);
        return true;
    }

    @Override
    public boolean updateQuestion(QuestionEntity question) {
        updateById(question);
        saveEs(question);
        return true;
    }


    private boolean saveEs(QuestionEntity question){
        //1.创建 Es model
        QuestionEsModel esModel = new QuestionEsModel();
        //2.复制属性
        //2.1 复制属性
        BeanUtils.copyProperties(question,esModel);
        //2.2获取“题目类型”的名称
        TypeEntity typeEntity = typeService.getById(question.getType());
        String typeName = typeEntity.getType();
        //2.3 给 ES model 的“类型”字段赋值
        esModel.setTypeName(typeName);
        System.out.println("------------esModel:" + esModel);

        //3.调用 passinterview-search 服务，将数据发送到 ES 中保存
        R r = searchFeignService.saveQuestion(esModel);
        System.out.println("r:" + r);

        return true;
    }

}