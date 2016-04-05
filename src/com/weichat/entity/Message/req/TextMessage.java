package com.weichat.entity.Message.req;

import com.weichat.entity.Message.req.BaseMessage;

public class TextMessage extends BaseMessage {
	
	//message content
	private String Content;
	
	public String getContent() {
		return Content;
	}
	
	public void setContent(String content) {
		this.Content = content;
	}
}
