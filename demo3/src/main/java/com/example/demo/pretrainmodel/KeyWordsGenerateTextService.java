package com.example.demo.pretrainmodel;

import jep.Jep;
import jep.JepException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class KeyWordsGenerateTextService {
   public String getText(String text) throws IOException {
       /** 创建HttpClient */
       CloseableHttpClient httpClient = HttpClients.createDefault();

       /** httpPost */
       //发送post请求
       HttpPost httpPost = new HttpPost("http://localhost:8000/index/");
       //将所有必要的信息传进请求的请求体
       List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
       String key ="key_words";
       String value = text;
       paramsList.add(new BasicNameValuePair(key,value));
       httpPost.setEntity(new UrlEncodedFormEntity(paramsList,"UTF-8"));
       //通过这个httpclient 发送请求 。
       CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
       HttpEntity entity = httpResponse.getEntity();
       String s = EntityUtils.toString(entity);
       System.out.println(s);
       return s;
   }


}
