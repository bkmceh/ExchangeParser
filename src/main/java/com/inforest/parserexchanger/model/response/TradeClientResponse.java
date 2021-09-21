package com.inforest.parserexchanger.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeClientResponse {

    private final String tickerName;

    private final String time;

    private final String price;

    public TradeClientResponse(String ticker, String time, String price) {
        this.tickerName = ticker;
        this.time = time;
        this.price = price;
    }

    @JsonProperty("ticker_name")
    public String getTickerName() {
        return tickerName;
    }

    public String getTime() {
        return time;
    }

    public String getPrice() {
        return price;
    }
}
