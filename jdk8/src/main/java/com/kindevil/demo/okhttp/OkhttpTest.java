package com.kindevil.demo.okhttp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.Charsets;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OkhttpTest {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/zhangxin/temp.txt");
        List<String> strings = Files.readLines(file, Charsets.UTF_8);
        JsonNode jsonNode = new ObjectMapper().readTree(strings.get(0));
        JsonNode result = jsonNode.get("content").get("data").get("page").get("result");


        for (int i = 0; i < 100; i++) {
            System.out.println("=================" + i);
            JsonNode job = result.get(i);
            System.out.println(job.get("createTime"));
            System.out.println(job.get("positionName"));
        }
    }

    public static void test(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        //Form表单格式的参数传递
        FormBody formBody = new FormBody
                .Builder()
                .add("companyId", "200554")//设置参数名称和参数值
                .add("positionFirstType", "全部")//设置参数名称和参数值
                .add("schoolJob", "false")//设置参数名称和参数值
                .add("pageNo", "1")//设置参数名称和参数值
                .add("pageSize", "20")//设置参数名称和参数值
                .build();

        Request request = new Request
                .Builder()
                .post(formBody)//Post请求的参数传递，此处是和Get请求相比，多出的一句代码</font>
                .url("https://www.lagou.com/gongsi/searchPosition.json")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();
            System.out.println(result);
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
