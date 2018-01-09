package com.json.tset;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author zhangxin
 *         Created on 17/10/26.
 */
public class ValueNodeSerialize extends JsonSerializer<ValueNode> {
    @Override
    public void serialize(ValueNode value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString("testssss");
    }
}
