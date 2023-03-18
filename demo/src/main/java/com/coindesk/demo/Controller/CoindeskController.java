package com.coindesk.demo.Controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.coindesk.demo.dto.CoindeskDto;
import com.coindesk.demo.dto.CoindeskReformDto;
import com.coindesk.demo.service.CurrencyNameService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(value="/coindesk", produces=MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CoindeskController {

	@Autowired
	CurrencyNameService currencyService;
	
	@GetMapping(value = "/")
	public String getCoinBaseData(HttpServletRequest request) {
		RestTemplate rest = new RestTemplate();
		String result = null;
		
		Gson gson = new Gson();
		CoindeskDto dto = null;
		try {
			result = rest.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);
		} catch (Exception e) {
			log.error("getCoinBaseData failed:",e);
		}		
		
		return result;
	}
	
	@GetMapping(value="/reform")
	public Map getCoinBaseDataReform(HttpServletRequest request) {
		Map<String, Object> resultMap = null;
		RestTemplate rest = new RestTemplate();
		String result = null;
		
		Gson gson = new Gson();
		CoindeskDto dto = null;
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-DD'T'HH:mm:ss");
		SimpleDateFormat resultFormat = new SimpleDateFormat("yyyy/MM/DD HH:mm:ss");
		
		try {
			result = rest.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);
			dto = gson.fromJson(result, CoindeskDto.class);
			
			CoindeskDto.UpdateTimeData data = null;
			data = dto.getTime();
			Date updateDate = dataFormat.parse(data.getUpdatedISO());

			CoindeskReformDto reformData = new CoindeskReformDto();
			reformData.setUpdateTime(resultFormat.format(updateDate));
			
			for ( String key : dto.getBpi().keySet() ) {
				CoindeskDto.BPI bpiData = dto.getBpi().get(key);
				CoindeskReformDto.CurrencyData currencyData = new CoindeskReformDto.CurrencyData();
				currencyData.setCurrencyCode(bpiData.getCode());
				currencyData.setCurrencyName(
						currencyService.getByCurrencyCode(bpiData.getCode()) == null ? null :
							currencyService.getByCurrencyCode(bpiData.getCode()).getName());
				currencyData.setRate(Float.valueOf(bpiData.getRate_float()));
				
				reformData.getCurrencyData().add(currencyData);
			}
			
			resultMap = new HashMap<String, Object>();
			resultMap.put("updateTime", resultFormat.format(updateDate));
			resultMap.put("reformData", reformData);
			
			result = gson.toJson(resultMap);
			
		} catch (Exception e) {
			log.error("getCoinBaseData failed:",e);
		}		
		
		return resultMap;
	}
	
	
	
}
