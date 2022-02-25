package io.opentelemetry.example.trade;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.example.trade.repository.entity.Trade;

@RestController
public class TradeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TradeController.class);

	private TradeService tradeService;

	public TradeController(TradeService tradeService) {
		this.tradeService = tradeService;
	}

	@RequestMapping("/trades")
	public List<Trade> trades(@RequestParam(value = "origin", defaultValue = "India") String origin) {
		LOGGER.info("processing Request");

		Iterable<Trade> trades = tradeService.getTrades(origin);
		List<Trade> result = new ArrayList<>();
		trades.forEach(result::add);
		return result;
	}
}
