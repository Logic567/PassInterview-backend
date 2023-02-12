package com.logic.passinterview.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.study.entity.ViewLogEntity;

import java.util.Map;

/**
 * 学习-用户学习浏览记录表
 *
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-13 00:44:24
 */
public interface ViewLogService extends IService<ViewLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

