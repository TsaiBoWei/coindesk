package com.coindesk.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "currency_name")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Data
public class CurrencyName implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3023152866818986180L;
    
	@Id
    @Column(name="currency_code")
    private String currency_code;
    
    @Column(name="name")
    private String name;
    
}