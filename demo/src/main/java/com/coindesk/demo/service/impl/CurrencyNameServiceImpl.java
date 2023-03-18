package com.coindesk.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coindesk.demo.dao.CurrencyNameDao;
import com.coindesk.demo.model.CurrencyName;
import com.coindesk.demo.service.CurrencyNameService;


@Service("currencyService")
public class CurrencyNameServiceImpl implements CurrencyNameService {
	
	@Autowired
	private CurrencyNameDao currencyNameDao;

	@Override
	public List<CurrencyName> getAll() {
		return currencyNameDao.findAll();
	}

	@Override
	public CurrencyName findById(String currencyCode) {
		Optional<CurrencyName> cn = currencyNameDao.findById(currencyCode);
		
		return cn.isPresent() ? cn.get():null;
	}

	@Override
	@Transactional
	public CurrencyName save(CurrencyName currency) {
		return currencyNameDao.save(currency);
	}

	@Override
	public CurrencyName getByCurrencyCode(String currencyCode) {
		
		return currencyNameDao.getOne(currencyCode);
	}

	@Override
	@Transactional
	public void update(CurrencyName currency) {
		
		CurrencyName old = getByCurrencyCode(currency.getCurrency_code());
		
		if ( StringUtils.isNotBlank(currency.getCurrency_code()) ) {
			old.setCurrency_code(currency.getCurrency_code());
		}
		
		if ( StringUtils.isNotBlank(currency.getName()) ) {
			old.setName(currency.getName());
		}
		
		currencyNameDao.saveAndFlush(old);
	}

	@Override
	@Transactional
	public void deleteByCurrencyCode(String currencyCode) {
		currencyNameDao.deleteById(currencyCode);
	}
	
	

}
