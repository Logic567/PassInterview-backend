package com.logic.passinterview.channel.service.impl;

import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.logic.passinterview.channel.dao.ChannelDao;
import com.logic.passinterview.channel.entity.ChannelEntity;
import com.logic.passinterview.channel.service.ChannelService;


@Service("channelService")
public class ChannelServiceImpl extends ServiceImpl<ChannelDao, ChannelEntity> implements ChannelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ChannelEntity> page = this.page(
                new Query<ChannelEntity>().getPage(params),
                new QueryWrapper<ChannelEntity>()
        );

        return new PageUtils(page);
    }

}