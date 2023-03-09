package com.logic.passinterview.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员-会员表
 *
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-13 00:38:45
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    MemberEntity getMemberByUserId(String userId);
}

