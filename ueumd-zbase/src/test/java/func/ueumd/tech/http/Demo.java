package func.ueumd.tech.http;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 发送GET请求
     */
    @Test
    public void getRequest() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://httpbin.org/get")
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        }

    }

    /**
     * POST请求
     */
    @Test
    public void postRequest() throws IOException {
        OkHttpClient client = new OkHttpClient();

        // 提交FormData
        FormBody.Builder form = new FormBody.Builder();
        form.add("name", "Tom");
        form.add("age", "23");

        Request request = new Request.Builder()
                .url("http://httpbin.org/post")
                .post(form.build())
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }


    @Test
    public void postRequest2() throws IOException {

        String url = "https://oauth2.googleapis.com/token";
        //String url = "http://httpbin.org/post";

        // 提交FormData
        RequestBody body = new FormBody.Builder().add("code", "4/0AVHEtk5OZ4Is_RA9_GPWChZ4EiXKBrojlE5Fv5cynIkU3AkAUuMntQMCGW_ap4nNgKWb7g")
                .add("client_id", "698495949929-vspaib419fn1hhfc89tnnqvrefj8t64c.apps.googleusercontent.com")
                .add("client_secret", "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")
                .add("redirect_uri", "https://developers.google.com")
                .add("grant_type", "authorization_code").build();


        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();

            String result = response.body().string();//得到数据
            JSONObject jsonObject = JSONObject.parseObject(result);//转为JSON格式
            System.out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCode() {
        String abc ="abcd";
        String abc1 ="abcd1";
        System.out.println(abc.equals(abc1));
    }
}