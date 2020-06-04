package com.pujacts.microservices.currencyconversionservicee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.pujacts.microservices.currencyconversionservicee")
public class CurrencyConversionServiceeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceeApplication.class, args);
	}

}
