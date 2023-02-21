package com.logic.passinterview.search.vo;

import lombok.Data;

/**
 * 查询 ES 数据的请求参数
 */
@Data
public class SearchParam {
    private String keyword; //全文匹配的关键字
    private String id; //题目id
    private Integer pageNum; //查询第几页数据
}
