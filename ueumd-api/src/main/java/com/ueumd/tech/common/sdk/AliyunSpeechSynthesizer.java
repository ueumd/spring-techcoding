package com.ueumd.tech.common.sdk;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-08 12:02
 *
 */
@Component
public class AliyunSpeechSynthesizer {

    private static final String url = "https://nls-gateway-cn-shanghai.aliyuncs.com/stream/v1/tts";

    private static final String accessToken = "0155624700334b9eaf1deaecf90e81ce";

    private final String appkey = "key";
    private final String voice = "voice";

    public void processRequest(String text, String audioSaveFile) {
        /**
         * 设置HTTPS POST请求：
         * 1.使用HTTPS协议
         * 2.语音合成服务域名：nls-gateway-cn-shanghai.aliyuncs.com
         * 3.语音合成接口请求路径：/stream/v1/tts
         * 4.设置必须请求参数：appkey、token、text、format、sample_rate
         * 5.设置可选请求参数：voice、volume、speech_rate、pitch_rate
         */

        JSONObject taskObject = new JSONObject();

        taskObject.put("appkey", appkey);
        taskObject.put("token", accessToken);
        taskObject.put("text", text);
        taskObject.put("format", "wav");
        taskObject.put("voice", voice);
        taskObject.put("sample_rate", 24000);
        // taskObject.put("speech_rate", 0);

        String bodyContent = taskObject.toJSONString();
        System.out.println("POST Body Content: " + bodyContent);
        RequestBody reqBody = RequestBody.create(MediaType.parse("application/json"), bodyContent);
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .post(reqBody)
                .build();
        try {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            String contentType = response.header("Content-Type");
            if ("audio/mpeg".equals(contentType)) {
                File f = new File(audioSaveFile);
                FileOutputStream fout = new FileOutputStream(f);
                fout.write(response.body().bytes());
                fout.close();
                System.out.println("The POST request succeed!");
            }
            else {
                // ContentType 为 null 或者为 "application/json"
                String errorMessage = response.body().string();
                System.out.println("The POST request failed: " + errorMessage);
            }
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
