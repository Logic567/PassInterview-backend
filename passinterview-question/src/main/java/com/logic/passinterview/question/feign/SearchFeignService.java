package com.logic.passinterview.question.feign;

import com.logic.passinterview.common.to.es.QuestionEsModel;
import com.logic.passinterview.common.utils.R;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Mapper
@FeignClient("passinterview-search")
public interface SearchFeignService {

    @PostMapping("search/question/save")
    R saveQuestion(@RequestBody QuestionEsModel questionEsModel);
}
