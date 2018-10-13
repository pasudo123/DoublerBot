package edu.doubler.service;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.doubler.service.weather.WeatherServiceImpl;

@Service
public class MessageServiceImpl implements MessageService {

	private static final String NAVER_BASIC_URL = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=";
	private static final String WETHER_WORD = "도씨";

	private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);
	private static final String WANT_WEATHER_MESG = "날씨";
	private static final String WANT_COMMNET_MESG = "안녕";

//	private WeatherServiceImpl weatherServiceImpl;

	@Override
	public String branchMessage(String message) {

		// 안녕 <-> 안녕
		if (WANT_COMMNET_MESG.equals(message))
			return WANT_COMMNET_MESG;

		// 날씨 <-> 날씨정보
		else if (message.contains(WANT_WEATHER_MESG))
			return getWeather(message);

		// 기타
		return message;
	}
	
	public String getWeather(String message) {

		StringBuilder answer = null;
		String newURL = null;
		Document document = null;

		try {

			answer = new StringBuilder();
			newURL = NAVER_BASIC_URL + message;

			Connection connection = Jsoup.connect(newURL);
			connection.userAgent("Mozilla/5.0");
			document = connection.get();

			Elements todayArea = document.getElementsByClass("today_area _mainTabContent");
			Elements tomorrowArea = document.getElementsByClass("tomorrow_area _mainTabContent");
			Elements weatherElements = document.getElementsByClass("info_list weather_condition _tabContent");

			// 현재온도, 내일(오전,오후)온도
			String todayTemperature = null;
			String tomorrowTemperature[] = new String[2];

			int index = 0;

			// 오늘
			for (Element element : todayArea) {
				Elements infoTemperature = element.getElementsByClass("info_temperature");
				todayTemperature = infoTemperature.text();
				todayTemperature = todayTemperature.replaceAll(WETHER_WORD, "");
			}

			// 내일
			for (Element element : tomorrowArea) {
				Elements infoTemperatures = element.getElementsByClass("info_temperature");

				for (Element subElement : infoTemperatures) {
					tomorrowTemperature[index] = subElement.text();
					tomorrowTemperature[index] = tomorrowTemperature[index].replaceAll(WETHER_WORD, "");
					index++;
				}
			}

			document = Jsoup.parse(weatherElements.html());
			Elements ulElements = document.getElementsByClass("list_area");
			Element firElement = ulElements.get(0); // 1 번째, 2 번째는 내일과 모레 시간

			/** ~날씨는 **/
			answer.append(message + "는");
			answer.append("\n");

			for (Element element : firElement.children()) {
				String lines[] = element.text().split(" ");

				String time = lines[lines.length - 1];
				String tprt = lines[2].substring(0, lines[2].length() - 1) + "℃";
				tprt = (tprt.length() != 3) ? "0" + tprt : tprt;
				String desc = lines[5];

				String line = time + " :: " + tprt + ", " + desc;
				answer.append(line);
				answer.append("\n");

//				System.out.println(time + " :: " + tprt + ", " + desc);
			}

//			System.out.println(message);
//			System.out.println("현재온도 : " + todayTemperature);
//			System.out.println("내일오전 : " + tomorrowTemperature[0] + ", 내일오후 : " + tomorrowTemperature[1]);
//			System.out.println("------");

			answer.append("현재온도 : " + todayTemperature);
			answer.append("\n");
			answer.append("내일오전 : " + tomorrowTemperature[0]);
			answer.append("\n");
			answer.append("내일오후 : " + tomorrowTemperature[1]);

			return answer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "날씨찾다가 에러";
		}
	}
}