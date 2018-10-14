package edu.doubler.dto;

public class MessagePhotoDto {
	private String url; 	// 이미지 URL
	private int width; 		// 이미지 너비
	private int height; 	// 이미지 높이

	public String getUrl() {
		return url;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
