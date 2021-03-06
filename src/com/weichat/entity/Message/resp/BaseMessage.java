package com.weichat.entity.Message.resp;

//backend -> user

public class BaseMessage {
	
	//user id
	private String ToUserName;
	
	//developer id
	private String FromUserName;
	
	//create time
	private long CreateTime;
	
	//message type
	private String MsgType;
	
	//message id
	private long MsgId;
	
	private int FuncFlag;
	
	public String getToUserName() {
		return ToUserName;
	}
	
	public void setToUserName(String toUserName) {
		this.ToUserName = toUserName;
	}
	
	public String getFromUserName() {
		return FromUserName;
	}
	
	public void setFromUserName(String fromUserName) {
		this.FromUserName = fromUserName;
	}
	
	public long getCreateTime() {
		return CreateTime;
	}
	
	public void setCreateTime(long createTime) {
		this.CreateTime = createTime;
	}
	
	public String getMsgType() {
		return MsgType;
	}
	
	public void setMsgType(String msgType) {
		this.MsgType = msgType;
	}
	
	public long getMsgId() {
		return MsgId;
	}
	
	public void setMsgId(long msgId) {
		this.MsgId = msgId;
	}
	
	public int getFuncFlag() {  
        return FuncFlag;  
    }  
  
    public void setFuncFlag(int funcFlag) {  
        FuncFlag = funcFlag;  
    }  
}
