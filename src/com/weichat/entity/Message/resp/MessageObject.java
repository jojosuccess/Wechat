package com.weichat.entity.Message.resp;

import java.util.Date;
import java.util.Map;

public class MessageObject {
	private String toUserName;
	private String fromUserName;
	
	public MessageObject() {
		
	}
	
	public MessageObject(Map<String, String> requestMap) {
		this.toUserName = requestMap.get("FromUserName");
		this.fromUserName = requestMap.get("ToUserName");
	}
	
	public TextMessage createTextMessage() {
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(toUserName);
		textMessage.setFromUserName(fromUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType("text");
		return textMessage;
	}
	
	public MusicMessage createMusicMessage() {
		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setToUserName(toUserName);
		musicMessage.setFromUserName(fromUserName);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMsgType("music");
		return musicMessage;
	}
}
