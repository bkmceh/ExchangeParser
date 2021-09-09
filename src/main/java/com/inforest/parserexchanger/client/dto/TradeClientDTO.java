package com.inforest.parserexchanger.client.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeClientDTO {

    @JsonAlias({"ticker_name"})
    private String ticker;

    private String time;

    @JsonAlias({"price"})
    private String price;

    public TradeClientDTO() { }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("ticker_name")
    public String getTicker() {
        return ticker;
    }

    public String getTime() {
        return time;
    }

    @JsonProperty("last_price")
    public String getPrice() {
        return price;
    }
}
