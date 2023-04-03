package com.example.demo.texttovoice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TextToVoiceProperties {
    @Value("${texttovoice.api_key}")
    String appKey;
    @Value("${texttovoice.api_secret}")
    String appSecret;
    @Value("${texttovoice.youdao_url}")
    String youDaoUrl;
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getYouDaoUrl() {
        return youDaoUrl;
    }

    public void setYouDaoUrl(String youDaoUrl) {
        this.youDaoUrl = youDaoUrl;
    }
}
