package com.logic.passinterview.channel.service.impl;

import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.logic.passinterview.channel.dao.AccessTokenDao;
import com.logic.passinterview.channel.entity.AccessTokenEntity;
import com.logic.passinterview.channel.service.AccessTokenService;


@Service("accessTokenService")
public class AccessTokenServiceImpl extends ServiceImpl<AccessTokenDao, AccessTokenEntity> implements AccessTokenService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccessTokenEntity> page = this.page(
                new Query<AccessTokenEntity>().getPage(params),
                new QueryWrapper<AccessTokenEntity>()
        );

        return new PageUtils(page);
    }

}