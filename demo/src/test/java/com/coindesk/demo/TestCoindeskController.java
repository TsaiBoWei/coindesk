package com.coindesk.demo;

import static org.assertj.core.api.Assertions.extractProperty;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import com.coindesk.demo.service.CurrencyNameService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestCoindeskController {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	CurrencyNameService currencyNameService;

	@Test
	@Order(1)
	public void testMyDbSelectSuccess() throws Exception {

		Gson gson = new Gson();
		String result = "[{\"currency_code\":\"EUR\",\"name\":\"歐元\"},{\"currency_code\":\"GBP\",\"name\":\"英鎊\"},{\"currency_code\":\"USD\",\"name\":\"美金\"}]";
		

		mockMvc.perform(MockMvcRequestBuilders.get("/currencyName/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json(result));
	}

	@Test
	@Order(2)
	public void testMyDbInsertSuccess() throws Exception {
		String currency_code = "test1";
		String name = "test1Name";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("currency_code", "test1");
		requestParams.add("name", "test1Name");
		mockMvc.perform(MockMvcRequestBuilders.post("/currencyName/").params(requestParams)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.currency_code").value(currency_code))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name));
	}

	@Test
	@Order(3)
	public void testMyDbUpdateSuccess() throws Exception {
		String currency_code = "EUR";
		String name = "EUR1";
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("currency_code", currency_code);
		requestParams.add("name", name);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/currencyName/update").params(requestParams)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.currency_code").value(currency_code))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name));
	}

	@Test
	@Order(4)
	public void testMyDbDeleteSuccess() throws Exception {
		String deleteTarget = "EUR";
		mockMvc.perform(MockMvcRequestBuilders.delete("/currencyName/" + deleteTarget)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().is2xxSuccessful());
	}

	@Test
	@Order(5)
	public void testCoindeskApiSuccess() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/coindesk/").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	@Order(6)
	public void testCoindeskReformApiSuccess() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/coindesk/reform").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().is2xxSuccessful());
	}
}
