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
		
//		weatherTest("통영날씨");
		weatherTest("서울날씨");
		
	}
	
	public static void weatherTest(String weatherQuery) throws IOException {
		String query = weatherQuery;
		
		String newURL = NAVER_BASIC_URL + query;
		System.out.println(newURL);
		
		Document document = Jsoup.connect(newURL).get();

		Elements todayArea = document.getElementsByClass("today_area _mainTabContent");
		Elements tomorrowArea = document.getElementsByClass("tomorrow_area _mainTabContent");
		Elements weatherElements = document.getElementsByClass("info_list weather_condition _tabContent");
		
		// 현재온도, 내일(오전,오후)온도
		String todayTemperature = null;					
		String tomorrowTemperature[] = new String[2];	
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
		
		document = Jsoup.parse(weatherElements.html());
		Elements ulElements = document.getElementsByClass("list_area");
		Element firElement = ulElements.get(0);	// 1 번째, 2 번째는 내일과 모레 시간
		
		for(Element element : firElement.children()) {
			String line[]= element.text().split(" ");
			
			String time = line[line.length - 1];
			String tprt = line[2].substring(0, line[2].length()-1) + "℃";
			tprt = (tprt.length() != 3)? "0" + tprt : tprt;
			String desc = line[5];
			
			System.out.println(time + " :: " + tprt + ", " + desc);
		}
		
		System.out.println(weatherQuery);
		System.out.println("현재온도 : " + todayTemperature);
		System.out.println("내일오전 : " + tomorrowTemperature[0] + ", 내일오후 : " + tomorrowTemperature[1]);
		System.out.println("------");
	}
}
