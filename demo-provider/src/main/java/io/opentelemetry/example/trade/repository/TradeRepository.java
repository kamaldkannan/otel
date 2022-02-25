package io.opentelemetry.example.trade.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.opentelemetry.example.trade.repository.entity.Trade;

@Repository
public interface TradeRepository extends CrudRepository<Trade, Long> {

}
