package func.ueumd.tech.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * OKHttp3
 * https://blog.csdn.net/gqg_guan/article/details/126603225
 */
public class OKHttpTest {

    @Test
    public void test() throws InterruptedException {
        //1. 构建OkHttpClient实例
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) //链接超时为2秒，单位为秒
                .writeTimeout(2, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.SECONDS)
                .build();

        // 2. 通过Builder辅助类构建请求对象
        final Request  request = new Request.Builder()
                .url("https://www.zhihu.com/api/v4/columns/c_1060581544644718592/items")
                .get()
                .build();

        System.out.println(request);

        // 创建线程，在子线程中运行
        new Thread(() -> {
            try {

                //3. 通过mOkHttpClient调用请求得到Call
                final Call call = okHttpClient.newCall(request);

                //4. 执行同步请求，获取响应体Response对象
                Response response = call.execute();
                System.out.println("response=" + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(5000);
    }

    @Test
    public void test2() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "https://oauth2.googleapis.com/token";

//        Map map = new HashMap();
//        map.put("code", "4/0AVHEtk5OZ4Is_RA9_GPWChZ4EiXKBrojlE5Fv5cynIkU3AkAUuMntQMCGW_ap4nNgKWb7g");
//        map.put("client_id", "698495949929-vspaib419fn1hhfc89tnnqvrefj8t64c.apps.googleusercontent.com");
//        map.put("client_secret", "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");
//        map.put("redirect_uri", "https://developers.google.com");
//        map.put("grant_type", "authorization_code");


        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("code", "4/0AVHEtk5OZ4Is_RA9_GPWChZ4EiXKBrojlE5Fv5cynIkU3AkAUuMntQMCGW_ap4nNgKWb7g")
                .add("client_id", "698495949929-vspaib419fn1hhfc89tnnqvrefj8t64c.apps.googleusercontent.com")
                .add("client_secret", "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")
                .add("redirect_uri", "https://developers.google.com")
                .add("grant_type", "authorization_code");

        RequestBody formBody = formBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                // .addHeader("Content-Type","application/form-data")
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println("返回码："+response.code());
        System.out.println(response.body().toString());

    }


}
