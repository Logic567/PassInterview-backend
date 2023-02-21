package com.logic.passinterview.search.vo;

import com.logic.passinterview.common.to.es.QuestionEsModel;
import lombok.Data;

import java.util.List;

/**
 * 查询 ES 数据的返回参数
 */
@Data
public class SearchQuestionResponse {
    private List<QuestionEsModel> questionList; //题目列表
    private Integer pageNum; //查询第几页数据
    private Long total; //总条数
    private Integer totalPages; //总页数
}
