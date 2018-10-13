package edu.doubler.bot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.doubler.api.KeyboardTextService;
import edu.doubler.api.MessageReceiver;
import edu.doubler.api.MessageSender;
import edu.doubler.dto.MessageDto;
import edu.doubler.service.MessageService;

@Controller
public class BotController {
	
	
	//reference : https://www.lesstif.com/pages/viewpage.action?pageId=24445183
	private static Logger logger = Logger.getLogger(BotController.class);
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/keyboard", method = RequestMethod.GET)
	@ResponseBody
	public String home() {

		ObjectMapper mapper = new ObjectMapper();
		
		KeyboardTextService keyboardTextService = new KeyboardTextService("text", "Welcome to the doubler-Bot");
		String jsonString = null;
		
		try {
			jsonString = mapper.writeValueAsString(keyboardTextService);
		} 
		catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
		if(jsonString != null) {
			logger.info(jsonString);
			return jsonString;
		}
		
		return null;
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String returnBotMessage(@RequestBody MessageReceiver userMessage) {
		
		logger.info(userMessage.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;

		MessageSender messageSender = new MessageSender();
		MessageDto messageDto = new MessageDto();

		String content = userMessage.getContent();
		String text = messageService.branchMessage(content);
		
		logger.info(text);
		messageDto.setText(text);
		messageSender.setMessage(messageDto);
		
		try {
			jsonString = mapper.writeValueAsString(messageSender);
		}
		catch(JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
		if(jsonString != null) {
			logger.info(jsonString);
			return jsonString;
		}
		
		return null;
	}
}
