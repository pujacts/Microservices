package com.pujacts.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pujacts.microservices.limitsservice.bean.LimitConfiguration;
import com.pujacts.microservices.limitsservice.cnfg.Configuration;

@RestController
public class LimitConfigurationController {

	@Autowired
	private Configuration cnfg;
	
	@GetMapping("/limit")
	public LimitConfiguration retrieveLimitConfig()
	{
		return new LimitConfiguration(cnfg.getMinimum(),cnfg.getMaximum());
	}
	
	@GetMapping("/faultToleranceEx")
	@HystrixCommand(fallbackMethod = "fallBackCnfg",commandProperties = {
			@HystrixProperty (name = "circuitBreaker.requestVolumeThreshold", value = "2"),
	          @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "500"),
	          @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "1"),
	          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000")

	  })
	public LimitConfiguration faultToleCnfg()
	{
		throw new RuntimeException("Testing Fault tolrance example");
	}
	
	public LimitConfiguration fallBackCnfg()
	{
		return new LimitConfiguration("9","999");
	}
	
}
