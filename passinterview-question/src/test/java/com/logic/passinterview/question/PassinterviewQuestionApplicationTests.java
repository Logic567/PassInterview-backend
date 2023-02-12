package com.logic.passinterview.question;

import com.logic.passinterview.question.entity.TypeEntity;
import com.logic.passinterview.question.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PassinterviewQuestionApplicationTests {

    @Autowired
    TypeService typeService;

    @Test
    void testCreateType() {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setType("javaBasic");
        typeService.save(typeEntity);
        System.out.println("创建成功");
    }

    @Test
    void testRemoveType(){
        typeService.removeById(2L);
        System.out.println("删除成功");
    }
}
