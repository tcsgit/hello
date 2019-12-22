package com.tcsg.hello.model;

public class Message {
				   
	// type => CHAT, JOIN, LEAVE
	private String type;
	private String content;
	private String senderid;
	private String sender;
	private String avatar;
	private String timestamp;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String serializeToPayload() {
        return type + "\\u0001" + content + "\\u0001" + senderid + "\\u0001" + sender + "\\u0001" + avatar + "\\u0001" + timestamp;
    }
	
	public String serializeToUserData() {
        return senderid + "~" + sender + "~" + avatar + "~" + timestamp;
    }

	public static Message deserializeFromPayload(String payload) {
		String[] data = payload.split("\\\\u0001");
		Message message = new Message();
		message.type = data[0];
		message.content = data[1];
		message.senderid = data[2];
		message.sender = data[3];
		message.avatar = data[4];
		message.timestamp = data[5];
        return message;
    }

	
}
