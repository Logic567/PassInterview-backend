package com.logic.passinterview.search.service;

import com.logic.passinterview.search.vo.SearchParam;
import com.logic.passinterview.search.vo.SearchQuestionResponse;

public interface IQuestionSearchService {
    SearchQuestionResponse search(SearchParam param);
}
