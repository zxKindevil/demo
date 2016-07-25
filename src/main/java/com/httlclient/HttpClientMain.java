package com.httlclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import java.io.InputStreamReader;

/**
 * @author zhangxin
 *         Created on 16/7/26.
 */
public class HttpClientMain {

    public static void main(String[] args) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8080/json/test");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            StringEntity s = new StringEntity(objectMapper.writeValueAsString(new A()));
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);

            HttpResponse res = client.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.OK.value()){
                HttpEntity entity = res.getEntity();
                String s1 = StreamUtils.copyToString(entity.getContent(), Charsets.UTF_8);
                A a = objectMapper.readValue(s1, A.class);
                System.out.println(a.getTest());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
class A {
    private String str = "a";
    private String test = "b";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
