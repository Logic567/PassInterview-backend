package com.logic.passinterview.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.study.entity.StudyTimeEntity;

import java.util.Map;

/**
 * 学习-用户学习时常表
 *
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-13 00:44:24
 */
public interface StudyTimeService extends IService<StudyTimeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

