package edu.doubler.bot.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NaverWeatherTester {
	
	static final String NAVER_BASIC_URL = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=";
	static final String WETHER_WORD = "도씨";
	
	public static void main(String[]args) throws IOException {
		
		test("통영날씨");
		test("서울날씨");
		
	}
	
	public static void test(String weatherQuery) throws IOException {
		String query = weatherQuery;
		
		String newURL = NAVER_BASIC_URL + query;
		Document document = Jsoup.connect(newURL).get();

		Elements todayArea = document.getElementsByClass("today_area _mainTabContent");
		Elements tomorrowArea = document.getElementsByClass("tomorrow_area _mainTabContent");
		
		String todayTemperature = null;					// 현재
		String tomorrowTemperature[] = new String[2];	// 오전 오후
		int index = 0;
		
		for(Element element : todayArea) {
			Elements infoTemperature = element.getElementsByClass("info_temperature");
			todayTemperature = infoTemperature.text();
			todayTemperature = todayTemperature.replaceAll(WETHER_WORD, "");
		}
		
		for(Element element : tomorrowArea) {
			Elements infoTemperatures = element.getElementsByClass("info_temperature");
			
			for(Element subElement : infoTemperatures) {
				tomorrowTemperature[index] = subElement.text();
				tomorrowTemperature[index] = tomorrowTemperature[index].replaceAll(WETHER_WORD, "");
				index++;
			}
		}
		
		System.out.println(weatherQuery);
		System.out.println("현재온도 : " + todayTemperature);
		System.out.println("내일오전 : " + tomorrowTemperature[0] + ", 내일오후 : " + tomorrowTemperature[1]);
		System.out.println("------");
	}
}
