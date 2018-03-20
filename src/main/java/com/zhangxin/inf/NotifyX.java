package com.zhangxin.inf;

import com.google.common.base.Strings;
import com.zhangxin.restapi.HttpWrapper;
import com.zhangxin.restapi.HttpWrapper$;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.entity.ContentType;

import java.io.IOException;

/**
 * @author zhangxin
 *         Created on 18/1/19.
 */
public class NotifyX {
    public static String url = "\u0006\u0017\u0001\u0006C\\\u0010\u0017\u001C\u001F\u0007_\u001A\u0017\u0013\u001E\u0006\u001A\\\u0011\u001D\u001F";
    public static String secret = "devil";


    public static void send(String content) {
        String url = xor(NotifyX.url, secret);
        url = "http://" + url + "/inner/wxcenter/custom/sendMsg/send";

        String json = "{\"appId\":\"wx4446e7bfdee3476b\",\"openId\":\"ootVqwLiVMOH5GI3soDEF4tYPnG4\",\"content\":\"zzzzzz\",\"type\":\"text\"}";
        json = json.replaceAll("zzzzzz", content);
        try {
            Request request = new Request.Builder().url(url).method("POST", RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)).build();
            HttpWrapper$.MODULE$.httpClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String xor(String str1, String str2) {
        if (Strings.isNullOrEmpty(str1)) {
            return str2;
        }
        if (Strings.isNullOrEmpty(str2)) {
            return str1;
        }

        byte b1[] = str1.getBytes();
        byte b2[] = str2.getBytes();
        byte longbytes[], shortbytes[];
        if (b1.length >= b2.length) {
            longbytes = b1;
            shortbytes = b2;
        } else {
            longbytes = b2;
            shortbytes = b1;
        }
        byte xorstr[] = new byte[longbytes.length];

        for (int i = 0; i < longbytes.length; i++) {
            xorstr[i] = longbytes[i];
            for (int j = 0; j < shortbytes.length; j++) {
                xorstr[i] = (byte) (xorstr[i] ^ shortbytes[j]);
            }
        }
        return new String(xorstr);
    }

    public static void main(String[] args) throws IOException {
        NotifyX.send("test");
    }

}
