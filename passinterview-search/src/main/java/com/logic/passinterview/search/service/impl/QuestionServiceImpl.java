package com.logic.passinterview.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.logic.passinterview.common.to.es.QuestionEsModel;
import com.logic.passinterview.search.config.EsConstant;
import com.logic.passinterview.search.service.IQuestionService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("questionService")
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public boolean save(QuestionEsModel questionEsModel) throws IOException {
        //创建数据到 ES 中
        IndexRequest request = new IndexRequest(EsConstant.QUESTION_INDEX);
        request.id(questionEsModel.getId().toString());
        String s = JSON.toJSONString(questionEsModel);
        request.source(s, XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response);
        return true;
    }
}
