package com.logic.passinterview.thirdparty.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.logic.passinterview.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/thirdparty/oss")
public class OssController {

    @Autowired
    OSS ossClient;

    @Value("${spring.cloud.alicloud.access-key}")
    private String accessId;

    @Value("${spring.cloud.alicloud.secret-key}")
    private String accessKey;

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endPoint;

    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;

    @RequestMapping("/getPolicy")
    public R getPolicy(){
        String host = "https://" + bucket + "." + endPoint;//host的格式为 bucketname.endpoint
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
//         String callbackUrl = "http://localhost:8001";
        String formatDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir = formatDate + "/";

        Map<String, String> respMap = new LinkedHashMap<>();
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConditions = new PolicyConditions();

            policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE,0,1048576000);
            policyConditions.addConditionItem(MatchMode.StartWith,PolicyConditions.COND_KEY,dir);

            String postPolicy = ossClient.generatePostPolicy(expiration,policyConditions);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap.put("accessid",accessId);
            respMap.put("policy",encodedPolicy);
            respMap.put("signature",postSignature);
            respMap.put("dir",dir);
            respMap.put("host",host);
            respMap.put("expire",String.valueOf(expireEndTime / 1000));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            ossClient.shutdown();
        }
        return R.ok().put("data",respMap);
    }
}
