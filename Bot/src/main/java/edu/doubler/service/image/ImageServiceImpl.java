package edu.doubler.service.image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.doubler.service.MessageServiceImpl;

public class ImageServiceImpl {
	
	private static final Logger logger = Logger.getLogger(ImageServiceImpl.class);
	private static final String GOOGLE_IMAGE_URL = "https://www.google.co.kr/search?tbm=isch&q=";
	private static final String VOID = "";
	
	public String getImage(String message) {
		
		String newURL = null;
		Document document = null;
		ArrayList<String> urlList = new ArrayList<String>();
		
		try {
			
			// 검색 시 "!토마토" 라고 한 경우 "!" 느낌표 제거 후 합침
			String searchImageName = message.substring(1, message.length());
			newURL = GOOGLE_IMAGE_URL + searchImageName;
			
			// Jsoup Connection 연결
			Connection connection = Jsoup.connect(newURL);
//			connection.userAgent("Chrome");
			connection.timeout(5000);
			document = connection.get();
			
			// 구글 이미지 검색 후 태그 탐색
			Elements images = document.getElementsByClass("rg_bx rg_di rg_el ivg-i");
			
			for(Element element : images) {
				
				Elements image = element.getElementsByTag("img");
				String imageUrl = image.attr("data-src");
				
				// attr 이 공백아닌 경우만 리스트 삽입 (image-url)
				if(!VOID.equals(imageUrl)) {
					urlList.add(imageUrl);
				}
			}
			
			// 0 ~ (size-1) 까지 난수로 하나의 이미지를 출력
			int size = urlList.size();
			int selectIndex = (new Random()).nextInt(size);	
			
			// 선택 이미지 반환
			String selectImageUrl = urlList.get(selectIndex);
			return selectImageUrl;
		}// try
		
		catch(IOException e) {
			e.printStackTrace();
			return null;
		}// catch
	}
	
}
