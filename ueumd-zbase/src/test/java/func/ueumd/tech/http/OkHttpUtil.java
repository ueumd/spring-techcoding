package func.ueumd.tech.http;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OkHttpUtil {
	public static String postMap(String url, Map<String,Object> param) {
		// 创建一个OkHttpClient对象
		OkHttpClient okHttpClient = new OkHttpClient();
 
		// 创建一个RequestBody(参数1：数据类型 参数2传递的json串)
		FormBody.Builder builder = new FormBody.Builder();
 
		for (String key : param.keySet()) {
			Object obj = param.get(key);
			if (obj != null) {
				builder.addEncoded(key, param.get(key).toString());
			} else {
				builder.addEncoded(key, "");
			}
		}
		FormBody  requestBody = builder.build();
 
		// 创建一个请求对象
		Request request = new Request.Builder().url(url).post(requestBody).build();
		// 发送请求获取响应
		try {
			Response response = okHttpClient.newCall(request).execute();
			// 判断请求是否成功
			if (response.isSuccessful()) {
				// 打印服务端返回结果
				return response.body().string();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{}";
	}
 
	public static String postMap1(String url,  Map<String,Object> param) {
		// 创建一个OkHttpClient对象
		OkHttpClient okHttpClient = new OkHttpClient();
 
		StringBuilder builder = new StringBuilder();
 
		param.forEach((name, value) ->{
			if (builder.length() != 0) {
				builder.append('&');
			}
			builder.append(name);
			if (value != null) {
				builder.append('=');
				builder.append(value);
			}
 
		});
 
		//return builder.toString();
 
		RequestBody requestBody = RequestBody.create(MediaType.get("application/x-www-form-urlencoded"), builder.toString());
 
		// 创建一个请求对象
		Request request = new Request.Builder().url(url).post(requestBody).build();
		// 发送请求获取响应
		try {
			Response response = okHttpClient.newCall(request).execute();
			// 判断请求是否成功
			if (response.isSuccessful()) {
				String body = response.body().string();
				// 打印服务端返回结果
				return body;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{}";
	}

	@Test
	public void test2() throws IOException {
		System.setProperty("http.proxyHost", "1270.0.0.1");
		System.setProperty("http.proxyPort", "7890");

		String url = "https://oauth2.googleapis.com/token";

		Map map = new HashMap();
		map.put("code", "4/0AVHEtk5OZ4Is_RA9_GPWChZ4EiXKBrojlE5Fv5cynIkU3AkAUuMntQMCGW_ap4nNgKWb7g");
		map.put("client_id", "698495949929-vspaib419fn1hhfc89tnnqvrefj8t64c.apps.googleusercontent.com");
		map.put("client_secret", "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");
		map.put("redirect_uri", "https://developers.google.com");
		map.put("grant_type", "authorization_code");

		String s = postMap(url, map);
		System.out.println(s);
	}


	@Test
	public void getGoogleAccessToken() {
		System.setProperty("http.proxyHost", "1270.0.0.1");
		System.setProperty("http.proxyPort", "7890");
		RestTemplate restTemplate = new RestTemplate();


		String url = "https://oauth2.googleapis.com/token";
		Map<String, String> params = new HashMap<>();
		params.put("code", "4/0AVHEtk5OZ4Is_RA9_GPWChZ4EiXKBrojlE5Fv5cynIkU3AkAUuMntQMCGW_ap4nNgKWb7g");
		params.put("client_id", "698495949929-vspaib419fn1hhfc89tnnqvrefj8t64c.apps.googleusercontent.com");
		params.put("client_secret", "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");
		params.put("redirect_uri", "https://developers.google.com");
		params.put("grant_type", "authorization_code");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, params, String.class);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			System.out.println(responseEntity);
		}
	}

}