package io.opentelemetry.example.trade.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.opentelemetry.example.trade.model.Trade;
import io.opentelemetry.example.trade.service.TradeService;
import io.opentelemetry.extension.annotations.WithSpan;

@Component
public class TradeMQConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TradeMQConsumer.class);

	@Autowired
	private TradeService tradeService;

	@Autowired
	private ObjectMapper mapper;

	@RabbitListener(queues = "#{'${rabbitmq.trade.received.queue}'}")
	@WithSpan
	public void consumeMessage(String tradeMessage) {
		try {
			LOGGER.trace("Message received: {} ", tradeMessage);
			Trade trade = create(tradeMessage);
			tradeService.process(trade);
			LOGGER.debug("Message processed successfully");
		} catch (Exception e) {
			LOGGER.error("Unnable to process the Message", e);
		}
	}

	private Trade create(String tradeMessage) throws JsonProcessingException {
		return mapper.readValue(tradeMessage, Trade.class);
	}
}
