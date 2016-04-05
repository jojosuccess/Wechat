package com.weichat.core;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.Element;  

import com.weichat.util.URLEncode;
import com.weichat.entity.Message.resp.Music;
import com.weichat.util.HttpRequestUtil;

public class BaiduMusicService {
	
	public static Music processMusic(String musicTitle, String musicAuthor) {
		String requestURL = "http://box.zhangmen.baidu.com/x?op=12&count=1&title=";
		
		String title = URLEncode.urlEncodeUTF8(musicTitle);
		String author = URLEncode.urlEncodeUTF8(musicAuthor);
		
		requestURL = requestURL+title+"$$"+author+"$$$$";
		
		requestURL = requestURL.replaceAll("\\+", "%20");
		
		InputStream inputStream = httpRequest(requestURL);
		
		Music music = parseMusic(inputStream);
		
		if(music != null) {
			music.setTitle(musicTitle);
			if(!musicAuthor.equals("")){
				music.setDescription(musicAuthor);
			}
			else{
				music.setDescription("来自百度音乐");
			}
		}
		return music;
	}
	
	public static InputStream httpRequest(String requestURL) {
		InputStream inputStream = null;
		try {
			URL url = new URL(requestURL);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  
            // 获得返回的输入流  
            inputStream = httpUrlConn.getInputStream(); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return inputStream;
	}
	
	public static Music parseMusic(InputStream inputStream) {
		Music music = null;
		try {
			SAXReader reader = new SAXReader();  
            Document document = reader.read(inputStream);  
            // 得到xml根元素  
            Element root = document.getRootElement();  
            // count表示搜索到的歌曲数  
            String count = root.element("count").getText();
            if(!count.equals("0")){
            	List<Element> urlList = root.elements("url");
            	List<Element> durlList = root.elements("durl");
            	String urlEncode = urlList.get(0).element("encode").getText();  
                String urlDecode = urlList.get(0).element("decode").getText();  
                
                String url = urlEncode.substring(0, urlEncode.lastIndexOf("/") + 1) + urlDecode;
                if (-1 != urlDecode.lastIndexOf("&")) {
                    url = urlEncode.substring(0, urlEncode.lastIndexOf("/") + 1) + urlDecode.substring(0, urlDecode.lastIndexOf("&"));  
                }
                
                String durl = url;
                music = new Music();
                music.setMusicUrl(url);
                music.setHQMusicUrl(url);
            }
		}
		catch(Exception e){
        	e.printStackTrace();
        }
        return music;
	}
}
