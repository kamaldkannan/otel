package io.opentelemetry.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.trade.dg.exchange}")
	private String exchange;

	@Value("${rabbitmq.trade.received.queue}")
	private String tradeQueue;
	@Value("${rabbitmq.trade.received.routingkey}")
	private String tradeRoutingKey;

	@Bean
	DirectExchange directExchange() {
		return new DirectExchange(exchange);
	}

	@Bean(name = "tradeReceived")
	Queue tradeReceivedQueue() {
		return new Queue(tradeQueue, true);
	}

	@Bean
	Binding tradeReceivedBinding(@Qualifier("tradeReceived") Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(tradeRoutingKey);
	}
}
