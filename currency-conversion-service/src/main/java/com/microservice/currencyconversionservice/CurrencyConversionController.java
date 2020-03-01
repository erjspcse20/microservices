package com.microservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy ceproxy;
	
	@GetMapping("currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from
			,@PathVariable String to,@PathVariable BigDecimal quantity) {
		Map<String,String> uriVariable = new HashMap<>();
		uriVariable.put("from",from);
		uriVariable.put("to",to);
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8920/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,uriVariable);
		CurrencyConversionBean resp =  responseEntity.getBody();
		return new CurrencyConversionBean(resp.getId(),from,to,resp.getConversionMultiple(),quantity,quantity.multiply(resp.getConversionMultiple()),resp.getPort());
	}
	
	@GetMapping("currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from
			,@PathVariable String to,@PathVariable BigDecimal quantity) {
		CurrencyConversionBean resp =  ceproxy.retriveExchangeValue(from, to);
		return new CurrencyConversionBean(resp.getId(),from,to,resp.getConversionMultiple(),quantity,quantity.multiply(resp.getConversionMultiple()),resp.getPort());
	}
}
