package com.logic.passinterview.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.logic.passinterview.common.to.es.QuestionEsModel;
import com.logic.passinterview.search.config.EsConstant;
import com.logic.passinterview.search.service.IQuestionSearchService;
import com.logic.passinterview.search.vo.SearchParam;
import com.logic.passinterview.search.vo.SearchQuestionResponse;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionSearchServiceImpl implements IQuestionSearchService {

    @Qualifier("restHighLevelClient")
    @Autowired
    private RestHighLevelClient client;

    @Override
    public SearchQuestionResponse search(SearchParam param) {

        SearchQuestionResponse questionResponse =new SearchQuestionResponse();
        SearchResponse response = null;
        /**
         * 动态构建出查询需要的 DSL 语句
         */
        // 1.1创建检索请求
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); // 构建 DSL 语句
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(param.getKeyword())){
            // 1.2 title, answer, typeName 字段模糊匹配
            boolQuery.must(QueryBuilders.multiMatchQuery(param.getKeyword(),"title","answer","typeName"));
        }

        if (param.getId() != null){
            // 1.3 题目 id 用来精确匹配
            boolQuery.filter(QueryBuilders.termQuery("id",param.getId()));
        }
        sourceBuilder.query(boolQuery);

        // 1.4 分页
        sourceBuilder.from((param.getPageNum() - 1) * EsConstant.PAGE_SIZE);
        sourceBuilder.size(EsConstant.PAGE_SIZE);

        SearchRequest request = new SearchRequest(new String[]{EsConstant.QUESTION_INDEX},sourceBuilder);

        try {
            // 2. 执行检索
            response = client.search(request, RequestOptions.DEFAULT);
            // 3. 分析结果
            System.out.println(response.toString());
            // 3.1 获取查询到的数据
            SearchHits hits = response.getHits();
            // 3.2 获取真正命中的数据
            SearchHit[] searchHits = hits.getHits();
            // 3.3 遍历命中结果
            List<QuestionEsModel> questionEsModelList = new ArrayList<>();
            if (searchHits != null && searchHits.length > 0){
                for (SearchHit hit : searchHits) {
                    String hitStr = hit.getSourceAsString();
                    QuestionEsModel questionEsModel = JSON.parseObject(hitStr, QuestionEsModel.class);
                    System.out.println(questionEsModel);
                    questionEsModelList.add(questionEsModel);
                }
                questionResponse.setQuestionList(questionEsModelList);

                //分页
                long total = hits.getTotalHits().value;
                questionResponse.setTotal(total);
                questionResponse.setPageNum(param.getPageNum());
                int totalPages = (int) total % EsConstant.PAGE_SIZE == 0 ? (int) total / EsConstant.PAGE_SIZE : (int) (total / EsConstant.PAGE_SIZE + 1);
                questionResponse.setTotalPages(totalPages);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return questionResponse;
    }
}
