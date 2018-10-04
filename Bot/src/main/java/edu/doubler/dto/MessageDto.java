package edu.doubler.dto;

public class MessageDto {
	private String text;						// 이용자에게 전송할 응답메세지
	private MessagePhotoDto photo;				// 이용자에게 전송할 이미지정보
	private MessageButtonDto message_button;	// 이용자에게 전송할 말풍선 하단 링크
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public MessagePhotoDto getPhoto() {
		return photo;
	}

	public void setPhoto(MessagePhotoDto photo) {
		this.photo = photo;
	}

	public MessageButtonDto getMessage_button() {
		return message_button;
	}

	public void setMessage_button(MessageButtonDto message_button) {
		this.message_button = message_button;
	}
}
