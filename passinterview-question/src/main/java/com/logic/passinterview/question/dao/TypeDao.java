package com.logic.passinterview.question.dao;

import com.logic.passinterview.question.entity.TypeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目-题目类型表
 * 
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-12 23:42:40
 */
@Mapper
public interface TypeDao extends BaseMapper<TypeEntity> {
	
}
