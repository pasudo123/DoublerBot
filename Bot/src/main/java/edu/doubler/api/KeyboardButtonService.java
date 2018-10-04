package edu.doubler.api;

public class KeyboardButtonService {
	private String type;
	private String button;
	
	public KeyboardButtonService(String typeValue, String buttonValue) {
		this.type = typeValue;
		this.button = buttonValue;
	}
	
	public String getType() {
		return type;
	}
	
	public String getButton() {
		return button;
	}
}
