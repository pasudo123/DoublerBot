package edu.doubler.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.doubler.api.MessageSender;
import edu.doubler.dto.MessageDto;
import edu.doubler.dto.MessagePhotoDto;
import edu.doubler.service.image.ImageServiceImpl;
import edu.doubler.service.weather.WeatherServiceImpl;

@Service
public class MessageServiceImpl implements MessageService {

	private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);
	private static final String WANT_WEATHER_MESG = "날씨";
	private static final String WANT_SAYHELLO_MESG = "안녕";
	private static final String WANT_COMMNET_MESG = "어여 히사시부리";
	private static final String WANT_IMAGE_MESG = "!";

	@Override
	public String branchMessage(String message) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		
		MessageSender messageSender = new MessageSender();
		MessageDto messageDto = new MessageDto();
		
		logger.info(message);
		
		// 안녕 <-> 어여 히사시부리
		if (WANT_SAYHELLO_MESG.equals(message)) {
			messageDto.setText(WANT_COMMNET_MESG);
			messageSender.setMessage(messageDto);
		}

		// 날씨 정보
		else if (message.contains(WANT_WEATHER_MESG)) {
			String text = (new WeatherServiceImpl()).getWeather(message);
			
			logger.info(text);
			
			messageDto.setText(text);
			messageSender.setMessage(messageDto);
		}
		
		// 이미지 정보 (! 포함)
		else if (message.startsWith(WANT_IMAGE_MESG)) {
			String imageURL = (new ImageServiceImpl()).getImage(message);
			
			logger.info(imageURL);
			
			MessagePhotoDto messagePhotoDto = new MessagePhotoDto();
			messagePhotoDto.setUrl(imageURL);
			messagePhotoDto.setWidth(300);
			messagePhotoDto.setHeight(200);
			messageDto.setPhoto(messagePhotoDto);
			messageSender.setMessage(messageDto);
		}
		
		// 그대로 말 따라하기
		else {
			messageDto.setText(message);
			messageSender.setMessage(messageDto);
		}
		
		// JSON 처리
		try {
			
			jsonString = mapper.writeValueAsString(messageSender);
			
		}
		
		catch(JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
		// 기타
		return jsonString;
	}
}
