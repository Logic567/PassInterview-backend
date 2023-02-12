package com.logic.passinterview.channel.controller;

import java.util.Arrays;
import java.util.Map;

import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logic.passinterview.channel.entity.ChannelEntity;
import com.logic.passinterview.channel.service.ChannelService;



/**
 * 渠道-渠道表
 *
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-13 00:17:00
 */
@RestController
@RequestMapping("channel/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = channelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ChannelEntity channel = channelService.getById(id);

        return R.ok().put("channel", channel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChannelEntity channel){
		channelService.save(channel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ChannelEntity channel){
		channelService.updateById(channel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		channelService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
