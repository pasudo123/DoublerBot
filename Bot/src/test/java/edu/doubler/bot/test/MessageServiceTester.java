package edu.doubler.bot.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import edu.doubler.service.MessageService;
import edu.doubler.service.MessageServiceImpl;

public class MessageServiceTester {
	
	
	private MessageService messageService;
	
	@Before
	public void setMock() {
		messageService = mock(MessageServiceImpl.class);
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(messageService);
		
		when(messageService.branchMessage("안녕")).thenReturn("안녕");
		assertEquals(messageService.branchMessage("안녕"), "안녕");
		
		when(messageService.branchMessage("몰라")).thenReturn("몰라");
		assertEquals(messageService.branchMessage("몰라"), "몰라");
		
		messageService.branchMessage("날씨");
		verify(messageService).branchMessage("날씨");
	}
}
