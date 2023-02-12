package com.logic.passinterview.study.dao;

import com.logic.passinterview.study.entity.StudyTimeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学习-用户学习时常表
 * 
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-13 00:44:24
 */
@Mapper
public interface StudyTimeDao extends BaseMapper<StudyTimeEntity> {
	
}
