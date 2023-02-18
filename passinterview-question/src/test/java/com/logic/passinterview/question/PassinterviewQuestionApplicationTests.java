package com.logic.passinterview.question;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.logic.passinterview.question.entity.TypeEntity;
import com.logic.passinterview.question.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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

//    @Test
//    void testUploadByOss() throws FileNotFoundException{
//        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//        String accessKeyId = "LTAI5tKf9WKtKV4WMH27rjEN";
//        String accessKeySecret = "UxNpAFV0TnX5yMWgUKvpV5WkE1SCaF";
//        String bucketName = "passinterview";
//
//        String localFile = "C:\\Users\\45754\\Desktop";
//        String fileKeyName = "coding_java.jpg";
//
//        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
//
//        InputStream inputStream = new FileInputStream(localFile);
//        ossClient.putObject(bucketName,fileKeyName,inputStream);
//
//        ossClient.shutdown();
//    }

    @Autowired
    OSSClient ossClient;

    @Test
    void testUploadByAlicloudOss() throws FileNotFoundException{
        String bucketName = "passinterview";
        String localFile = "C:\\Users\\45754\\Desktop\\Snipaste.png";
        String fileKeyName = "Snipaste.png";
        InputStream inputStream = new FileInputStream(localFile);
        ossClient.putObject(bucketName,fileKeyName,inputStream);
        ossClient.shutdown();
    }
}
