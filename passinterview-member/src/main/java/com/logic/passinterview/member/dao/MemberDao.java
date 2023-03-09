package com.logic.passinterview.member.dao;

import com.logic.passinterview.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员-会员表
 * 
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-13 00:38:45
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
    MemberEntity getMemberByUserId(String userId);
}
