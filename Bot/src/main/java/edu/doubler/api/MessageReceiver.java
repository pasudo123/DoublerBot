package edu.doubler.api;

public class MessageReceiver {
	private String user_key;
	private String type;
	private String content;
	
	// -- setter();
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setContent(String content) {
		this.content = content;
	}
		
	//-- getter()
	public String getUser_key() {
		return user_key;
	}

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "MessageReceiver [user_key=" + user_key + ", type=" + type + ", content=" + content + "]";
	}
}