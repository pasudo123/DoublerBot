package edu.doubler.bot.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageGetterTester {
	
	static final String GOOGLE_IMAGE_URL = "https://www.google.co.kr/search?tbm=isch&q=";
	
	public static void main(String[]args) {
		
		String searchImageUrl = "박근혜";
		searchImageUrl = GOOGLE_IMAGE_URL + searchImageUrl;

		ArrayList<String> urlList = new ArrayList<String>();
		Document document = null;
		
		try {
			
			Connection connection = Jsoup.connect(searchImageUrl).timeout(5000);
//			connection.userAgent("Chrome");
			document = connection.get();
			
			// 구글 검색이후 이미지 창
			Elements images = document.getElementsByClass("rg_bx rg_di rg_el ivg-i");
			
			for(Element element : images) {
				Elements image = element.getElementsByTag("img");
				String imageUrl = image.attr("data-src");
				
				// attr 이 공백 제외하고 리스트 삽입
				if(!("".equals(imageUrl))) {
					urlList.add(imageUrl);
				}
			}
			
			// 0 ~ (size-1) 까지 난수로 하나의 이미지를 출력
			int size = urlList.size();
			int selectIndex = (new Random()).nextInt(size);	
			String selectImageUrl = urlList.get(selectIndex);
			
			System.out.println(selectImageUrl);
		}
		
		catch(IOException e) {
			e.printStackTrace();
			return;
		}
	}
}
