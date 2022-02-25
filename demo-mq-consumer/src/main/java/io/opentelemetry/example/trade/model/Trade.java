package io.opentelemetry.example.trade.model;

import java.util.Date;

public class Trade {

	private String origin;
	private String destination;
	private String tradeId;
	
	private Date executionTime;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}

	@Override
	public String toString() {
		return "Trade [origin=" + origin + ", destination=" + destination + ", tradeId=" + tradeId + ", executionTime="
				+ executionTime + "]";
	}	
}
