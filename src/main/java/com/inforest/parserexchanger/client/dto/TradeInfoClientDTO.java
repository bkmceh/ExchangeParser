package com.inforest.parserexchanger.client.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeInfoClientDTO {

    @JsonAlias({"response"})
    private TradeClientDTO tradeClientDTO;

    public TradeInfoClientDTO() { }

    public void setTradeClientDTO(TradeClientDTO tradeClientDTO) {
        this.tradeClientDTO = tradeClientDTO;
    }

    @JsonProperty("response")
    public TradeClientDTO getTradeClientDTO() {
        return tradeClientDTO;
    }
}
