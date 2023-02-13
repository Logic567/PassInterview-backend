package com.logic.passinterview.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.common.utils.R;
import com.logic.passinterview.member.feign.StudyTimeFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logic.passinterview.member.entity.MemberEntity;
import com.logic.passinterview.member.service.MemberService;



/**
 * 会员-会员表
 *
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-13 00:38:45
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private StudyTimeFeignService studyTimeFeignService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/studytime/list/test/{id}")
    public R getMemberStudyTimeListTest(@PathVariable("id") Long id){
        //mock数据库查到的会员信息
        MemberEntity memberEntity = new MemberEntity();
        //学习时长：100分钟
        memberEntity.setId(id);
        memberEntity.setNickname("与天争锋");

        //远程调用拿到该用户的学习时长
        R memberStudyTimeList = studyTimeFeignService.getMemberStudyTimeListTest(id);
        return R.ok().put("member",memberEntity).put("studytime",memberStudyTimeList.get("studytime"));
    }

}
