package com.eduonline.eduoss.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 在服务器启动读取配置
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    /**
     * 服务器启动时调用该方法
     *
     * @throws Exception
     */

    @Value("${aliyun.oss.file.endpoint}")
    public String endpoint;
    @Value("${aliyun.oss.file.keyId}")
    public String keyId;
    @Value("${aliyun.oss.file.keySecret}")
    public String keySecret;
    @Value("${aliyun.oss.file.bucketName}")
    public String bucketName;


    public static String ENDPOINT;
    public static String KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        KEYID = keyId;
        KEYSECRET = keySecret;
        BUCKETNAME = bucketName;
    }
}
