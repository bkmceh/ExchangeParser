package com.inforest.parserexchanger.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeInfoDTO {

    private TradeDTO trades;

    public TradeInfoDTO() {}

    public TradeDTO getTrades() {
        return trades;
    }

    public void setTrades(TradeDTO trades) {
        this.trades = trades;
    }

    @Override
    public String toString() {
        return "TradeInfoDTO{" +
                "trades=" + trades +
                '}';
    }
}
