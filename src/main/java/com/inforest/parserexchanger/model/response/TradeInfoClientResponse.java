package com.inforest.parserexchanger.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeInfoClientResponse {
    private final TradeClientResponse response;

    public TradeInfoClientResponse(TradeClientResponse response) {
        this.response = response;
    }

    @JsonProperty("response")
    public TradeClientResponse getTradeClientResponse() {
        return response;
    }
}
