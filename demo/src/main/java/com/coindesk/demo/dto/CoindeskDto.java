package com.coindesk.demo.dto;

import java.util.Map;

import lombok.Data;

@Data
public class CoindeskDto {
	
	Map<String, BPI> bpi;
	
	String chartName;
	
	String disclaimer;
	
	UpdateTimeData time;	
	
	@Data
	public static class BPI {
		String code;
		String description;
		String rate;
		String rate_float;
		String symbol;
		String currency_name;
	}
	
	@Data
	public static class UpdateTimeData {
		String updated;
		String updatedISO;
		String updateduk;
	}
}
