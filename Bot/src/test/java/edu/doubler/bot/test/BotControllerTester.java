package edu.doubler.bot.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.doubler.api.MessageReceiver;
import edu.doubler.bot.BotController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( locations = {"file:\\src\\main\\webapp\\WEB-INF\\spring\\**\\*.xml"} )
public class BotControllerTester {
	
	private static final Logger logger = Logger.getLogger(BotControllerTester.class);

	private MockMvc mockMvc;
	
//	@Autowired(required = true)
//	MessageService messageService;
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BotController()).build();
        logger.info("mockMvc, standaloneSetup(new BotController()).build");
    }
	
	@Test
	public void weatherMessageService() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		MessageReceiver messageReceiver = new MessageReceiver();
		
		messageReceiver.setUser_key("encryptedUserKey");
		messageReceiver.setType("text");
		messageReceiver.setContent("날씨");
		
		logger.info(mapper.writeValueAsString(messageReceiver));
		
		this.mockMvc.perform(post("/message")
			.content(mapper.writeValueAsString(messageReceiver))
			.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andDo(print())
			.andExpect(status().is(415));
		
		logger.info("mockMvc, perform POST - /message");
	}
	
}