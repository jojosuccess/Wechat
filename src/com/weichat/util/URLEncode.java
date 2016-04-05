package com.weichat.util;

import java.io.UnsupportedEncodingException;

public class URLEncode {

	public static String urlEncodeUTF8(String url) {
		String result = url;  
        try {  
            result = java.net.URLEncoder.encode(url, "utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return result;  
	}
}
