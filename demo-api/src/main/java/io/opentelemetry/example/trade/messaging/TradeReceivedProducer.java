package io.opentelemetry.example.trade.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.opentelemetry.example.trade.Trade;

@Component
public class TradeReceivedProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TradeReceivedProducer.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${rabbitmq.trade.dg.exchange}")
	private String exchangeName;

	@Value("${rabbitmq.trade.received.routingkey}")
	private String routingKey;
	
	@Autowired
	private ObjectMapper objectMapper;

	public void sendMessage(Trade trade) {
		try {
			String jsonMessage = objectMapper.writeValueAsString(trade);
			rabbitTemplate.convertAndSend(exchangeName, routingKey, jsonMessage);
			LOGGER.debug("Sent message for trade");
		} catch (Exception e) {
			LOGGER.error("Unable to send Message ", e);
		}
	}
}