package com.coindesk.demo.service;

import java.util.List;

import com.coindesk.demo.model.CurrencyName;

public interface CurrencyNameService {

	public List<CurrencyName> getAll();
	
	public CurrencyName findById(String currencyCode);
	
	public CurrencyName save(CurrencyName currency);
	
	public CurrencyName getByCurrencyCode(String currencyCode);
	
	public void update(CurrencyName currency);
	
	public void deleteByCurrencyCode(String currencyCode);	
	
}
