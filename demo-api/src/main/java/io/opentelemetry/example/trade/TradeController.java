package io.opentelemetry.example.trade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(TradeController.class);
	
	private TradeService tradeService;
	
	public TradeController(TradeService tradeService) {
		this.tradeService = tradeService;
	}
	
	@GetMapping("/trades")
    public List<Trade> greeting(@RequestParam(value = "origin", defaultValue = "Markit") String origin) {
    	LOGGER.info("Before Service Method Call");
        return tradeService.getTrades(origin);
    }

}
