package com.logic.passinterview.search.service;

import com.logic.passinterview.common.to.es.QuestionEsModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Mapper
@Component
public interface IQuestionService {
    boolean save(QuestionEsModel questionEsModel) throws IOException;
}
