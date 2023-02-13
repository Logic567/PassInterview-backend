package com.logic.passinterview.question.service.impl;

import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.common.utils.Query;
import org.apache.commons.lang3.StringUtils;
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

}