package edu.doubler.api;

import edu.doubler.dto.MessageDto;

public class MessageSender{
	MessageDto message;

	public MessageDto getMessage() {
		return message;
	}

	public void setMessage(MessageDto message) {
		this.message = message;
	}
}
