package com.logic.passinterview.thirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class PassinterviewThirdpartyApplicationTests {

    @Autowired
    OSSClient ossClient;

    @Test
    void testUploadByAlicloudOss() throws FileNotFoundException {
        String bucketName = "passinterview";
        String localFile = "C:\\Users\\45754\\Desktop\\Snipaste.png";
        String fileKeyName = "hahaha.png";
        InputStream inputStream = new FileInputStream(localFile);
        ossClient.putObject(bucketName,fileKeyName,inputStream);
        ossClient.shutdown();
    }

}
