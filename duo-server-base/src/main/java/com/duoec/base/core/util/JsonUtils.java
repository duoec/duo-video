package com.duoec.base.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuwenzhen
 */
public class JsonUtils {
    private static ObjectMapper objectMapper = getObjectMapper();
    private static ObjectMapper objectMapperForWeb = getObjectMapperForWeb();

    private JsonUtils() {
    }

    /**
     * 获取ObjectMapper
     *
     * @return ObjectMapper实例
     */
    public static ObjectMapper getObjectMapper() {
        if (objectMapper != null) {
            return objectMapper;
        }
        synchronized (JsonUtils.class) {
            if (objectMapper != null) {
                return objectMapper;
            }

            objectMapper = new ObjectMapper();
            objectMapper
                    //反序列化时，忽略目标对象没有的属性
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

                    //注册时间、日期格式化
                    .registerModule(new JavaTimeModule())

                    //下面配置是值为null时，不显示
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);

            //下面一个配置是集合返回为空时，不显示
//                .configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false)
        }
        return objectMapper;
    }

    public static ObjectMapper getObjectMapperForWeb() {
        if (objectMapperForWeb != null) {
            return objectMapperForWeb;
        }
        synchronized (JsonUtils.class) {
            objectMapperForWeb = new ObjectMapper()
                    //反序列化时，忽略目标对象没有的属性
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                    .enable(DeserializationFeature.ACCEPT_FLOAT_AS_INT)
                    //下面配置是值为null时，不显示
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)

                    //注册时间、日期格式化
                    .registerModule(new JavaTimeModule());

            registryLongConverter(objectMapperForWeb);
        }
        return objectMapperForWeb;
    }

    /**
     * 对象转jsonString
     *
     * @param obj 待转换的对象
     * @return 解析后的字符串
     */
    public static String toJsonString(Object obj) {
        if (obj == null) {
            return "";
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json序列化失败：{}", e);
        }
    }

    /**
     * jsonString转Object
     *
     * @param str   jsonString
     * @param clazz 待转换的Class类型
     * @param <T>   泛型
     * @return
     */
    public static <T> T toObject(String str, Class<T> clazz) {
        if (null == str || "".equals(str.trim())) {
            return null;
        }
        try {
            return objectMapper.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json反序列化失败：{}", e);
        }
    }

    /**
     * jsonString转List
     *
     * @param str   jsonString
     * @param clazz 待转换的类型
     * @param <T>
     * @return
     */
    public static <T> List<T> toObjectList(String str, Class<T> clazz) {
        if (null == str || "".equals(str.trim())) {
            return new ArrayList<>();
        }
        JavaType javaType = getCollectionType(List.class, clazz);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json反序列化失败：{}", e);
        }
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 仅供测试时使用，正式代码中不要使用
     *
     * @param objectMapper ObjectMapper实例
     * @deprecated
     */
    public static void setObjectMapper(ObjectMapper objectMapper) {
        JsonUtils.objectMapper = objectMapper;
    }

    private static void registryLongConverter(ObjectMapper objectMapper) {
        SimpleModule longConverter = new SimpleModule();
        longConverter.addSerializer(Long.class, ToStringSerializer.instance);
        longConverter.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(longConverter);
    }
}
