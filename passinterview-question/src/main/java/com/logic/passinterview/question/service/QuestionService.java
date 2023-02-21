package com.logic.passinterview.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logic.passinterview.common.utils.PageUtils;
import com.logic.passinterview.question.entity.QuestionEntity;

import java.util.Map;

/**
 * 
 *
 * @author logic
 * @email 457547174@qq.com
 * @date 2023-02-12 23:42:40
 */
public interface QuestionService extends IService<QuestionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean saveQuestion(QuestionEntity question);

    boolean updateQuestion(QuestionEntity question);
}

