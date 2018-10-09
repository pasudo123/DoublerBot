package edu.doubler.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

	private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);
	private static final String WANT_WEATHER_MESG = "날씨";
	private static final String WANT_COMMNET_MESG = "안녕";
	
	@Override
	public String branchMessage(String message) {

		// 안녕 <-> 안녕
		if(WANT_COMMNET_MESG.equals(message))
			return WANT_COMMNET_MESG;
		
		// 날씨 <-> 날씨정보
		else if(WANT_WEATHER_MESG.equals(message))
			return getWeatherProcess();
			
		// 기타
		return message;
	}
	
	private String getWeatherProcess() {
		
		logger.info("날씨 호출");
		
		return WANT_WEATHER_MESG;
	}
}
