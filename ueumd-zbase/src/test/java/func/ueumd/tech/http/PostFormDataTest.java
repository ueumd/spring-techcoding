package func.ueumd.tech.http;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
 
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
 
/**
 * multipart/form-data方式测试
 * @author ouyangjun
 */
public class PostFormDataTest {
	
    public static void main(String[] args) {
        // 请求地址
        String url = "https://oauth2.googleapis.com/token";
    	
        // 请求参数
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("code", "4/0AVHEtk5OZ4Is_RA9_GPWChZ4EiXKBrojlE5Fv5cynIkU3AkAUuMntQMCGW_ap4nNgKWb7g");
        paramsMap.put("client_id", "698495949929-vspaib419fn1hhfc89tnnqvrefj8t64c.apps.googleusercontent.com");
        paramsMap.put("client_secret", "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");
        paramsMap.put("redirect_uri", "https://developers.google.com");
        paramsMap.put("grant_type", "authorization_code");

        System.setProperty("http.proxyHost", "1270.0.0.1");
        System.setProperty("http.proxyPort", "7890");
        httpMethod(url, paramsMap);
    }
    
    public static void httpMethod(String url, Map<String, String> paramsMap) {
        // 创建client对象 创建调用的工厂类 具备了访问http的能力
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS) // 设置超时时间
                .readTimeout(10, TimeUnit.SECONDS) // 设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS) // 设置写入超时时间
                .build();
    	
        // 添加请求类型
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MediaType.parse("multipart/form-data"));
    	
        //  创建请求的请求体
        for (String key : paramsMap.keySet()) {
            // 追加表单信息
            builder.addFormDataPart(key, paramsMap.get(key));
        }
        RequestBody body = builder.build();
    	
        // 创建request, 表单提交
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
    	
        // 创建一个通信请求
        try (Response response = client.newCall(request).execute()) {
            // 尝试将返回值转换成字符串并返回
            System.out.println("==>返回结果: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}