package io.opentelemetry.example.trade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.example.trade.messaging.TradeReceivedProducer;
import io.opentelemetry.extension.annotations.WithSpan;

@Service
public class TradeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TradeService.class);
	
	private TradeClient tradeClient;
	private TradeReceivedProducer producer;
	
	public TradeService(TradeClient tradeClient, TradeReceivedProducer producer) {
		this.tradeClient = tradeClient;
		this.producer = producer;
	}

	public List<Trade> getTrades(String origin) {
		LOGGER.info("Getting trades for {}", origin);
		List<Trade> trades = this.tradeClient.getTrades(origin);
		send(trades);
		doSomeWorkNewSpan();
		return trades;
	}

	private void send(List<Trade> trades) {
		trades.forEach(trade -> {
			this.producer.sendMessage(trade);
		});		
	}

	@WithSpan
    private void doSomeWorkNewSpan() {
		LOGGER.info("Doing some work In New span");
        Span span = Span.current();
 
        span.setAttribute("attribute.a2", "some value");
 
        span.addEvent("app.processing2.start", atttributes("321"));
        span.addEvent("app.processing2.end", atttributes("321"));
    }
 
    private Attributes atttributes(String id) {
        return Attributes.of(AttributeKey.stringKey("app.id"), id);
    }
}
