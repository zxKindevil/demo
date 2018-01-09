package com.json.tset;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.Map;

/**
 * @author zhangxin
 *         Created on 17/10/26.
 */
public class JsonMain {
    public static void main(String[] args) throws IOException {
        String json = "{\n" +
                "    \"transactionId\": \"mnrc_60048280270000316562\",\n" +
                "    \"relateTransactionId\": \"mnc_600482802700\",\n" +
                "    \"amount\": 0.01,\n" +
                "    \"refundType\": \"WeChat\",\n" +
                "    \"ext\": {}\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(ValueNode.class, new ValueNodeSerialize());
        mapper.registerModule(module);

        Map map = mapper.readValue(json, Map.class);

        System.out.println(map);

        String string = mapper.writeValueAsString(map);

        System.out.println(string);

        System.out.println(mapper.writeValueAsString(new ValueNode()));
    }
}
