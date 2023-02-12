package com.logic.passinterview.content.dao;

import com.logic.passinterview.content.entity.NewsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 内容-资讯表
 * 
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-13 00:29:16
 */
@Mapper
public interface NewsDao extends BaseMapper<NewsEntity> {
	
}
