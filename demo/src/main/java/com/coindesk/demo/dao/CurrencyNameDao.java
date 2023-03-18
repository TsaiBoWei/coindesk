package com.coindesk.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coindesk.demo.model.CurrencyName;

public interface CurrencyNameDao extends JpaRepository<CurrencyName, String> {

}
