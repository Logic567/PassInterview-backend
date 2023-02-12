package com.logic.passinterview.channel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logic.passinterview.channel.entity.ChannelEntity;
import com.logic.passinterview.common.utils.PageUtils;

import java.util.Map;

/**
 * 渠道-渠道表
 *
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-13 00:17:00
 */
public interface ChannelService extends IService<ChannelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

