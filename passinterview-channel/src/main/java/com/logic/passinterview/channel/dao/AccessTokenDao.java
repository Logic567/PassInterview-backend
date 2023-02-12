package com.logic.passinterview.channel.dao;

import com.logic.passinterview.channel.entity.AccessTokenEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 渠道-认证表
 * 
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-13 00:17:00
 */
@Mapper
public interface AccessTokenDao extends BaseMapper<AccessTokenEntity> {
	
}
