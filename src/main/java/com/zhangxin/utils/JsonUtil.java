package com.zhangxin.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.MapType;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxin on 2018/1/14.
 */
public class JsonUtil {

    private JsonUtil() {
    }

    public static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        init(objectMapper);
    }

    public static void init(ObjectMapper objectMapper) {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        SimpleModule module = new SimpleModule("DateTimeModule", Version.unknownVersion());

        objectMapper.registerModule(module);
    }

    public static ObjectMapper instance() {
        return objectMapper;
    }

    public static String of(Object o) {
        if (null == o) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw Throwables.propagate(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T of(String json, Class<T> tClass) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, tClass);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> T of(String json, TypeReference<T> reference) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        try {
            return objectMapper.readerFor(reference).readValue(json);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> T of(String json, Class<T> clazz, Module module) throws IOException {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        try {
            objectMapper.registerModule(module);
            return objectMapper.readerFor(clazz).readValue(json);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> List<T> ofList(String json, Class<T> tClass) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(ArrayList.class, ArrayList.class,
                tClass);
        try {
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static <K, V> Map<K, V> ofMap(String json, Class<K> keyClass, Class<V> valueClass) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        MapType mapType = objectMapper.getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass);
        try {
            return objectMapper.readValue(json, mapType);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static String toJson(Object obj) {
        if (null == obj) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Throwable t) {
            Throwables.propagate(t);
        }
        return null;
    }
}
