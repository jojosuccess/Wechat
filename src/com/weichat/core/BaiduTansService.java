package com.weichat.core;

import java.io.BufferedReader;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;  
import java.net.HttpURLConnection;  
import java.net.URL;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import com.google.gson.Gson;  


import com.weichat.translation.TranslateResult;
import com.weichat.util.HttpRequestUtil;


public class BaiduTansService {
	
	private static final String AppId = "20160325000016580";
	
	private static final String token = "8DKqfBzuAwpSnEnZCTQ3";
		
	private static final String url = "http://api.fanyi.baidu.com/api/trans/vip/translate";
	
	private static final Random random = new Random();

	public static String processTrans(String source) {
		String dst = null;
		int salt = random.nextInt(10000);
		
		String keyWord = urlEncodeUTF8(source);
		StringBuffer md5String = new StringBuffer();
		
		md5String.append(AppId).append(source).append(salt).append(token);
		
		String md5 = DigestUtils.md5Hex(md5String.toString());
		String requestURL = url + "?q=" + keyWord + "&from=zh&to=en&appid=" + AppId + "&salt="+ salt + "&sign=" + md5;
		try {  
            // 查询并获取返回结果  
            String json = HttpRequestUtil.httpRequest(requestURL);  
            // 通过Gson工具将json转换成TranslateResult对象  
            TranslateResult translateResult = new Gson().fromJson(json, TranslateResult.class);  
            // 取出translateResult中的译文  
            dst = translateResult.getTransResult().get(0).getDst();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		if (null == dst)  
            dst = "翻译系统异常，请稍候尝试！";  
        return dst;
	}
	
	public static String httpRequest(String requestURL) {  
        StringBuffer buffer = new StringBuffer();  
        try {  
            URL url = new URL(requestURL);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
  
            httpUrlConn.setDoOutput(false);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
  
            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  
  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
  
        } 
        catch (Exception e) {  
        }  
        return buffer.toString();  
    }  
	public static String urlEncodeUTF8(String source) {  
        String result = source;  
        try {  
            result = java.net.URLEncoder.encode(source, "utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
}
