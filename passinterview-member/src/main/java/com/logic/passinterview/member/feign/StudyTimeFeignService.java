package com.logic.passinterview.member.feign;

import com.logic.passinterview.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("passinterview-study")
public interface StudyTimeFeignService {

    @RequestMapping("study/studytime/member/list/test/{id}")//调用的方法的路径
    public R getMemberStudyTimeListTest(@PathVariable("id") Long id);
}
