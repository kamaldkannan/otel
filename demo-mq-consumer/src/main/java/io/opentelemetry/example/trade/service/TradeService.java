package io.opentelemetry.example.trade.service;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import io.opentelemetry.example.trade.model.Trade;
import io.opentelemetry.extension.annotations.WithSpan;

@Service
public class TradeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TradeService.class);

	private StringRedisTemplate stringRedisTemplate;

	public TradeService(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

	@WithSpan
	public void process(Trade trade) {
		LOGGER.info("Processing : {}", trade);
		valExpire("valExpireKey");		
	}

	private void valExpire(String key) {
		stringRedisTemplate.opsForValue().set(key, "SomeValue", Duration.ofSeconds(1));
	}
}
