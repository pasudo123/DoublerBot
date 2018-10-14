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

import edu.doubler.service.image.ImageServiceImpl;
import edu.doubler.service.weather.WeatherServiceImpl;

@Service
public class MessageServiceImpl implements MessageService {

	private static final String NAVER_BASIC_URL = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=";
	private static final String WETHER_WORD = "도씨";

	private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);
	private static final String WANT_WEATHER_MESG = "날씨";
	private static final String WANT_COMMNET_MESG = "안녕";
	private static final String WANT_IMAGE_MESG = "!";
	

	@Override
	public String branchMessage(String message) {

		// 안녕 <-> 안녕
		if (WANT_COMMNET_MESG.equals(message)) {
			return WANT_COMMNET_MESG;
		}

		// 날씨 <-> 날씨정보
		else if (message.contains(WANT_WEATHER_MESG)) {
			return (new WeatherServiceImpl()).getWeather(message);
		}
		
		// 이미지 정보
		else if (message.startsWith(WANT_IMAGE_MESG)) {
			return (new ImageServiceImpl()).getImage(message);
		}
		
		// 기타
		return message;
	}
}
