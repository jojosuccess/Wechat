package com.weichat.entity.Message.resp;

public class InfoMessage {
	public static String getTranslateUsage() {
		StringBuffer buffer = new StringBuffer();  
	    buffer.append("中译英服务").append("\n"); 
	    buffer.append("输入：翻译＋中文单词").append("\n"); 
	    buffer.append("使用示例：").append("\n");  
	    buffer.append("    翻译我是中国人").append("\n");    
	    return buffer.toString();  
	}
	
	public static String getBaiduMusicUsage() {
		StringBuffer buffer = new StringBuffer();  
	    buffer.append("音乐搜索服务").append("\n"); 
	    buffer.append("输入：音乐＋歌名@作者 或者 输入：音乐＋歌名").append("\n"); 
	    buffer.append("使用示例：").append("\n");  
	    buffer.append("    输入小苹果").append("\n");  
	    buffer.append("    输入小苹果@筷子兄弟").append("\n");
	    return buffer.toString();  
	}
}
