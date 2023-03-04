package com.logic.passinterview.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.question.entity.TypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 题目-题目类型表
 *
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-12 23:42:40
 */
public interface TypeService extends IService<TypeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<TypeEntity> getTypeEntityList();

    List<TypeEntity> getTypeEntityListByLock();

    List<TypeEntity> getTypeEntityListByRedisDistributedLock() throws InterruptedException;

    List<TypeEntity> getTypeEntityListByRedissonDistributedLock();
}

