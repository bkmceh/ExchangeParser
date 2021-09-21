package com.inforest.parserexchanger.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inforest.parserexchanger.model.dto.TradeInfoDTO;
import org.apache.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResponseMapper {

    private final ObjectMapper MAPPER = new ObjectMapper();

    private List<String> tradeData = new ArrayList<>();

    public void initMapping(final String jsonResponse, final String ticker) {
        try {
            this.tradeData = MAPPER.readValue(jsonResponse, TradeInfoDTO.class).getTrades().getData().get(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.SC_NOT_FOUND, String.format("Could not find data for ticker: %s", ticker), e);
        }
    }

    public String getTicker() {
        return tradeData.get(3);
    }

    public String getTime() {
        return tradeData.get(9);
    }

    public String getPrice() {
        return tradeData.get(4);
    }
}
