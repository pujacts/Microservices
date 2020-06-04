package com.pujacts.microservices.currencyExchangeService.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pujacts.microservices.currencyExchangeService.bean.ExchangeValue;
import com.pujacts.microservices.currencyExchangeService.repo.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {

	private Logger logger=LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	private Environment env;

	@Autowired
	ExchangeValueRepository exchangeValRepo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchgVal =exchangeValRepo.findByFromAndTo(from, to) ;
		logger.info("ExchangeValue ->{}",exchgVal);
		exchgVal.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return exchgVal;
	}
}
