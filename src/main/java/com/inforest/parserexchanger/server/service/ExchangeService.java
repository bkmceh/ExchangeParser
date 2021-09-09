package com.inforest.parserexchanger.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inforest.parserexchanger.server.model.dto.TradeInfoDTO;
import com.inforest.parserexchanger.server.model.response.TradeClientResponse;
import com.inforest.parserexchanger.server.model.response.TradeInfoClientResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeService {

    private final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();

    public TradeInfoClientResponse getLastTradePrice(String ticker) {
        List<String> tradeData = new ArrayList<>();
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            CloseableHttpResponse httpResponse = client.execute(createRequest(ticker));
            String jsonResponse = EntityUtils.toString(httpResponse.getEntity());
            tradeData = MAPPER.readValue(jsonResponse, TradeInfoDTO.class).getTrades().getData().get(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.SC_NOT_FOUND, String.format("Could not find data for ticker: %s", ticker), e);
        }
        return new TradeInfoClientResponse(new TradeClientResponse(
                tradeData.get(3),
                tradeData.get(9),
                tradeData.get(4)));
    }

    private HttpUriRequest createRequest(String ticker) {
        return new HttpGet(String.format("https://iss.moex.com/iss/engines/stock/markets/shares/securities/" +
                "%s/trades.json?limit=1&reversed=1", ticker));
    }
}
