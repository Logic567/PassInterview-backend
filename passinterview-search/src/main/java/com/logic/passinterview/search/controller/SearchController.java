package com.logic.passinterview.search.controller;

import com.logic.passinterview.search.service.IQuestionSearchService;
import com.logic.passinterview.search.vo.SearchParam;
import com.logic.passinterview.search.vo.SearchQuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/search")
@RestController
public class SearchController {

    @Autowired
    IQuestionSearchService questionSearchService;

    @PostMapping("/question/list")
    public SearchQuestionResponse list(SearchParam param){
        return questionSearchService.search(param);
    }
}
