package edu.doubler.api;

public class KeyboardTextService {
	private String type;
	private String text;
	
	public KeyboardTextService(String typeValue, String textValue) {
		this.type = typeValue;
		this.text = textValue;
	}
	
	public String getType() {
		return type;
	}
	
	public String getText() {
		return text;
	}
}
