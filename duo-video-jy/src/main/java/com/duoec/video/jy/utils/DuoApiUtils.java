package com.duoec.video.jy.utils;

import com.duoec.base.core.DuoServerConsts;
import com.duoec.base.core.util.HmacUtils;
import com.duoec.base.core.util.JsonUtils;
import com.duoec.base.core.util.UrlUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class DuoApiUtils {
    public static final String BASE_JY_RESOURCE_API = "https://api.duoec.com/api/jy/resource/";
    private static final String SECRET_ID;
    private static final String SECRET_KEY;

    static {
        SECRET_ID = Optional.ofNullable(System.getenv("DUO_API_KEY")).orElse(DuoServerConsts.EMPTY_STR);
        SECRET_KEY = Optional.ofNullable(System.getenv("DUO_SECRET_KEY")).orElse(DuoServerConsts.EMPTY_STR);
    }

    /**
     * 请求 duo-api 接口
     * @param url 接口链接
     */
    public static <T extends Serializable> T get(String url, TypeReference<T> typeReference) {
        return get(url, null, typeReference);
    }

    /**
     * 请求 duo-api 接口，带授权信息
     * @param url 接口链接
     * @param authorization 授权信息
     */
    public static <T extends Serializable> T get(String url, String authorization, TypeReference<T> typeReference) {
        try {
            // 创建HttpClient实例
            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(30))
                    .build();

            // 获取当前时间戳（秒）
            long timestamp = System.currentTimeMillis() / 1000;
            String timestampStr = String.valueOf(timestamp);

            // 解析URL以获取路径和查询参数
            URI uri = URI.create(url);
            String path = uri.getPath();

            // 处理查询参数：按字典序排序并编码，与服务器端逻辑一致
            String queryString = uri.getQuery();
            if (queryString != null && !queryString.isEmpty()) {
                // 解析查询参数
                String[] pairs = queryString.split("&");
                Map<String, String> queryParams = new LinkedHashMap<>();

                for (String pair : pairs) {
                    int idx = pair.indexOf("=");
                    String key = idx > 0 ? pair.substring(0, idx) : pair;
                    String value = idx > 0 && pair.length() > idx + 1 ? pair.substring(idx + 1) : "";

                    // 使用与服务器端相同的编码方法
                    key = UrlUtils.encode(key);
                    value = UrlUtils.encode(value);

                    queryParams.put(key, value);
                }

                // 按键排序
                String[] sortedKeys = queryParams.keySet().toArray(new String[0]);
                Arrays.sort(sortedKeys);

                // 重新构建查询字符串
                StringBuilder sortedQueryString = new StringBuilder();
                for (int i = 0; i < sortedKeys.length; i++) {
                    if (i > 0) {
                        sortedQueryString.append("&");
                    }
                    String key = sortedKeys[i];
                    String value = queryParams.get(key);
                    sortedQueryString.append(key).append("=").append(value);
                }

                path += "?" + sortedQueryString.toString();
            }


            // 构建HTTP请求
            HttpRequest.Builder urlBuilder = HttpRequest.newBuilder()
                    .timeout(Duration.ofSeconds(30))
                    .uri(URI.create(url));
            if (StringUtils.hasLength(SECRET_ID)) {
                // 计算签名
                String signature = sign("GET", path, authorization, null, timestampStr);
                urlBuilder
                        .header(DuoServerConsts.STR_SECRET_ID, SECRET_ID)
                        .header(DuoServerConsts.STR_SIGNATURE, signature)
                        .header(DuoServerConsts.STR_TIMESTAMP, timestampStr);
            }
            HttpRequest request = urlBuilder
                    .GET()
                    .build();

            // 发送请求
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // 检查响应状态码
            String body = response.body();
            if (response.statusCode() != 200) {
                throw new DuoServiceException("API request failed with status code: " + response.statusCode() + ", response: " + body);
            }

            if (!StringUtils.hasLength(body)) {
                throw new DuoServiceException("请求响应为空！");
            }

            // 使用Jackson反序列化响应体到指定类型
            return JsonUtils.toObject(body, typeReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String sign(String method, String path, String authorization, String body, String timestampStr) {
        String signatureMateStr = SECRET_ID + DuoServerConsts.TURN_LINE
                + timestampStr + DuoServerConsts.TURN_LINE
                + method + DuoServerConsts.TURN_LINE
                + path + DuoServerConsts.TURN_LINE
                + Optional.ofNullable(authorization).orElse(DuoServerConsts.EMPTY_STR) + DuoServerConsts.TURN_LINE
                + Optional.ofNullable(body).orElse(DuoServerConsts.EMPTY_STR) + DuoServerConsts.TURN_LINE;
        String signature = HmacUtils.calculateHmacSha256(SECRET_KEY, signatureMateStr);


        return signature;
    }

}
