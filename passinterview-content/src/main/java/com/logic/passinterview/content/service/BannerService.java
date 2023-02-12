package com.logic.passinterview.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.content.entity.BannerEntity;

import java.util.Map;

/**
 * 内容-横幅广告表
 *
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-13 00:29:16
 */
public interface BannerService extends IService<BannerEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

