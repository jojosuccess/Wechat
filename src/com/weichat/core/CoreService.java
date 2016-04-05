package com.weichat.core;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.weichat.constant.Constant;
import com.weichat.util.MessageUtil;

import com.weichat.core.BaiduTansService;
import com.weichat.entity.Message.resp.*;


public class CoreService {
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try{	
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			MessageObject message = new MessageObject(requestMap);
			
			String content = requestMap.get("Content");
			if(requestMap.get("MsgType").equals(Constant.REQ_MESSAGE_TYPE_TEXT)) {
				if (content.startsWith("翻译")) {
					//TextMessage textMessage = message.createTextMessage();
					
					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(requestMap.get("FromUserName"));
					textMessage.setFromUserName(requestMap.get("ToUserName"));
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType("text");
					
			        String keyWord = content.replaceAll("翻译", "").trim();  
			        if (keyWord.length() == 0) {  
			            textMessage.setContent(InfoMessage.getTranslateUsage());  
			        } else { 
			            textMessage.setContent(BaiduTansService.processTrans(keyWord));  
			        }
			        respMessage = MessageUtil.textMessageToXml(textMessage);
			    }
				else if(content.startsWith("音乐")) {
					String keyWord = content.replaceAll("音乐", "").trim();
					String[] kwArr = keyWord.split("@"); 
					String musicTitle = kwArr[0];  
                    // 演唱者默认为空  
                    String musicAuthor = "";  
                    if (2 == kwArr.length)  
                        musicAuthor = kwArr[1]; 
                    Music music = BaiduMusicService.processMusic(musicTitle, musicAuthor);
					if(keyWord.length() == 0) {
						TextMessage textMessage = message.createTextMessage();
						textMessage.setContent(InfoMessage.getBaiduMusicUsage());
						respMessage = MessageUtil.textMessageToXml(textMessage);
					}
					else if(music == null) {
						TextMessage textMessage = message.createTextMessage();
						textMessage.setContent("对不起,没有找到你想要的歌曲<" + musicTitle + (kwArr.length==2?"@" + musicAuthor: "") + ">");
						respMessage = MessageUtil.textMessageToXml(textMessage);
					}
					else {
						MusicMessage musicMessage = message.createMusicMessage();
						musicMessage.setMusic(music);
						respMessage = MessageUtil.musicMessageToXml(musicMessage);
					}
				}
				else{
					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(requestMap.get("FromUserName"));
					textMessage.setFromUserName(requestMap.get("ToUserName"));
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType("text");
					
					switch(content){
					case "1":
						textMessage.setContent("1 is good");
						break;
					default:
						textMessage.setContent("back to menue");
					}
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace(); 
		}
		return respMessage;
	}
}
