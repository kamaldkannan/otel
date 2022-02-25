package io.opentelemetry.example.trade;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.opentelemetry.extension.annotations.WithSpan;

@Component
public class TradeClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(TradeClient.class);

	@Value("${app.provider1.url}")
	private String provider1Url;

	@Autowired
	private RestTemplate restTemplate;

	@WithSpan
	public List<Trade> getTrades(String origin) {
		LOGGER.info("Getting Trades from {}", provider1Url);

		ResponseEntity<Trade[]> response = restTemplate.getForEntity(provider1Url, Trade[].class);

		return Arrays.asList(response.getBody());
	}
}
