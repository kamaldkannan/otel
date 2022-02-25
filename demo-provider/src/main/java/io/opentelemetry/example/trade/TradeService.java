package io.opentelemetry.example.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.example.trade.repository.TradeRepository;
import io.opentelemetry.example.trade.repository.entity.Trade;
import io.opentelemetry.extension.annotations.WithSpan;

@Service
public class TradeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TradeService.class);
	
	private TradeRepository tradeRepository;
	
	public TradeService(TradeRepository repository) {
		this.tradeRepository = repository;
	}

	public Iterable<Trade> getTrades(String origin) {
		doSomeWorkNewSpan();
		return tradeRepository.findAll();
	}

	@WithSpan
    public void doSomeWorkNewSpan() {
		LOGGER.info("Doing some work In New span");
        Span span = Span.current();
 
        span.setAttribute("template.a2", "some value");
 
        span.addEvent("template.processing2.start", atttributes("321"));
        span.addEvent("template.processing2.end", atttributes("321"));
    }
 
    private Attributes atttributes(String id) {
        return Attributes.of(AttributeKey.stringKey("app.id"), id);
    }
}
