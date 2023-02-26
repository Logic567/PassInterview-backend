package com.logic.passinterview.question.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.logic.passinterview.question.dao.TypeDao;
import com.logic.passinterview.question.entity.TypeEntity;
import com.logic.passinterview.question.service.TypeService;

import javax.annotation.Resource;


@Service("typeService")
public class TypeServiceImpl extends ServiceImpl<TypeDao, TypeEntity> implements TypeService {

    @Qualifier("stringRedisTemplate")
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TypeEntity> page = this.page(
                new Query<TypeEntity>().getPage(params),
                new QueryWrapper<TypeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<TypeEntity> getTypeEntityList() {
        //1.初始化 redis 组件
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //2.从缓存中查询数据
        String typeEntityListCache = ops.get("typeEntityList");
        //3.如果缓存中没有数据
        if (StringUtils.isEmpty(typeEntityListCache)) {
            System.out.println("The cache is empty");
            //4.从数据库中查询数据
            List<TypeEntity> typeEntityListFromObj = this.list();
            //5.将从数据库中查询的数据序列化 JSON 字符串
            typeEntityListCache = JSON.toJSONString(typeEntityListFromObj);
            //6.将序列化后的数据存入缓存中
            ops.set("typeEntityList", typeEntityListCache);
            return typeEntityListFromObj;
        }
        //7.如果缓存中有数据，则从缓存中拿出来，并反序列化为实例对象
        List<TypeEntity> typeEntityList = JSON.parseObject(typeEntityListCache, new TypeReference<List<TypeEntity>>() {
        });
        return typeEntityList;
    }

    @Override
    public List<TypeEntity> getTypeEntityListByLock() {
        //1.从缓存中查询数据
        String typeEntityListCache = stringRedisTemplate.opsForValue().get("typeEntityList");
        if (!StringUtils.isEmpty(typeEntityListCache)) {
            //2.如果缓存中有数据，则从缓存中拿出来，并反序列化为实例对象，并返回结果
            List<TypeEntity> typeEntityList = JSON.parseObject(typeEntityListCache, new TypeReference<List<TypeEntity>>() {
            });
            return typeEntityList;
        }
        synchronized (this) {
            return getDataFromDB();
        }
    }

    private List<TypeEntity> getDataFromDB() {
        String typeEntityListCache;
        typeEntityListCache = stringRedisTemplate.opsForValue().get("typeEntityList");
        if (!StringUtils.isEmpty(typeEntityListCache)){
            //5.如果缓存中有数据，则从缓存中拿，并反序列化，返回
            List<TypeEntity> typeEntityList = JSON.parseObject(typeEntityListCache, new TypeReference<List<TypeEntity>>() {
            });
            return typeEntityList;
        }
        //6.如果没有，从数据库中取
        System.out.println("The cache is empty");
        List<TypeEntity> typeEntityListFromDb = this.list();
        //7.反序列化
        typeEntityListCache = JSON.toJSONString(typeEntityListFromDb);
        //8.存入缓存
        stringRedisTemplate.opsForValue().set("typeEntityList",typeEntityListCache,1,TimeUnit.DAYS);
        return typeEntityListFromDb;
    }

}