package com.coindesk.demo.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.coindesk.demo.model.CurrencyName;
import com.coindesk.demo.service.CurrencyNameService;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(value="/currencyName", produces=MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class MydbController {
	
	@Autowired
	CurrencyNameService currencyNameService;
	
	@PostMapping
	public CurrencyName create(HttpServletRequest request, @RequestParam Map<String,Object> params ) {
		try {
			CurrencyName currencyName = new CurrencyName();
			currencyName.setCurrency_code(params.get("currency_code").toString());
			currencyName.setName(params.get("name").toString());
			currencyNameService.save(currencyName);
			return currencyNameService.findById(currencyName.getCurrency_code());	
		} catch (Exception e) {
			log.error("getCoinBaseData failed:",e);
		}		
		return null;
	}
	
	@GetMapping("/{currencyCode}")
	public CurrencyName get(HttpServletRequest request, @PathVariable("currencyCode") String currencyCode) {
		try {
			return currencyNameService.findById(currencyCode);			
		} catch (Exception e) {
			log.error("getCoinBaseData failed:",e);
		}		
		return null;
	}
	
	@GetMapping("/")
	public List<CurrencyName> getAll(HttpServletRequest request) {
		List<CurrencyName> currencyList = null;
		try {			
			currencyList = currencyNameService.getAll();
			return currencyList;
		} catch (Exception e) {
			log.error("getCoinBaseData failed:",e);
		}		
		
		return null;
	}
	
	@PostMapping("/update")
	public CurrencyName update(HttpServletRequest request, @RequestParam Map<String,String> params) {
		try {
			CurrencyName currencyName = new CurrencyName();
			currencyName.setCurrency_code(params.get("currency_code"));
			currencyName.setName(params.get("name"));
			currencyNameService.update(currencyName);
			currencyName = currencyNameService.findById(currencyName.getCurrency_code());
			return currencyName;
		} catch (Exception e) {
			log.error("getCoinBaseData failed:",e);
		}		
		
		return null;
	}
	
	@DeleteMapping("/{currencyCode}")
	public void delete(HttpServletRequest request, @PathVariable String currencyCode) {

		try {
			currencyNameService.deleteByCurrencyCode(currencyCode);
		} catch (Exception e) {
			log.error("getCoinBaseData failed:",e);
		}		
		
	}
}
