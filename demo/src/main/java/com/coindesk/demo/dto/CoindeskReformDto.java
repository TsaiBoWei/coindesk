package com.coindesk.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CoindeskReformDto {
	
	String updateTime;
	
	List<CurrencyData> currencyData = new ArrayList<CurrencyData>();

	@Data
	@NoArgsConstructor
	public static class CurrencyData {
		String currencyCode;
		String currencyName;
		Float rate;
	}

}
