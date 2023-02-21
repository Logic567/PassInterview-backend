package com.logic.passinterview.common.to.es;

import lombok.Data;

/**
 *  "properties": {
 *     "id": {
 *         "type": "long"
 *     }
 *     "title": {
 *         "type": "keyword",
 *         "analyzer": "ik_smart"
 *     },
 *     "answer": : {
 *         "type": "keyword",
 *         "analyzer": "ik_smart"
 *        },
 * 	"typeName": {
 *         "type": "keyword",
 *     }
 */
@Data
public class QuestionEsModel {
    private Long id;
    private String title;
    private String answer;
    private String typeName;
}
