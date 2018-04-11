package com.aus.util;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by xy on 2017/10/20.
 */
public class HttpUtil {

    public static final String TEXT_PLAIN = "text/plain";
    public static final String APPLICATION_JSON = "application/json;charset=utf-8";

    private static OkHttpClient client = new OkHttpClient();

    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String post(String url, String json) {
        return post(url, json, APPLICATION_JSON);
    }

    public static String post(String url, String json, String media) {
        try {
            RequestBody body = RequestBody.create(MediaType.parse(media), json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (Exception e){
            throw new RuntimeException("连接服务器失败.");
        }
    }

}
