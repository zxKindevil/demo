package com.kindevil.demo.okhttp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.Charsets;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class OkhttpTest {

    public static void main(String[] args) throws IOException {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("companyId", "200554");
        map.put("positionFirstType", "全部");
        map.put("schoolJob", "false");
        map.put("pageNo", "1");
        map.put("pageSize", "20");

        Document ret = Jsoup.connect("https://www.lagou.com/gongsi/searchPosition.json")
                .method(Connection.Method.POST)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                .header("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
                .data(map)
                .cookie("cookie", "_ga=GA1.2.497251197.1517658632; user_trace_token=20180203195032-70220014-08d8-11e8-a69b-525400f775ce; LGUID=20180203195032-70220793-08d8-11e8-a69b-525400f775ce; JSESSIONID=ABAAABAAAGFABEF65B20F1EEA60EA2D2C6D314FFFDC76F3; _gid=GA1.2.1584489656.1535796586; index_location_city=%E5%8C%97%E4%BA%AC; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1535796586,1535797807,1535826203; LGSID=20180902192455-d1a407a3-aea2-11e8-b416-5254005c3644; SEARCH_ID=5ecdd6575dd144fba33084de8d072fa0; TG-TRACK-CODE=hpage_code; _gat=1; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1535889287; LGRID=20180902195446-fcfb8886-aea6-11e8-81e9-525400f775ce")
                .ignoreContentType(true)
                .get();

        System.out.println(ret.body().toString());

    }

    public static void testb(String[] args) throws IOException {
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
