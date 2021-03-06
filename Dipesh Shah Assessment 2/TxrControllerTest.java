package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;

import com.example.demo.model.Account;
import com.example.demo.service.TxrService;
import com.example.demo.web.TxrController;

@WebMvcTest (value = TxrController.class)
@WithMockUser
public class TxrControllerTest {

	@InjectMocks
	private TxrController txrController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TxrService txrService;
	
	Account account = new Account("1",2000);
	
	@Test
	public void txr() throws Exception{
		
		Mockito.when(txrService.txr(Mockito.anyDouble(), Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/txr").accept(MediaType.APPLICATION_JSON);			;
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse());
		assertEquals(result.getResponse(),HttpStatus.BAD_REQUEST);
	}
}
