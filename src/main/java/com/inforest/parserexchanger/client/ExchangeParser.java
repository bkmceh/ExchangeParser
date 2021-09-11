package com.inforest.parserexchanger.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.inforest.parserexchanger.client.dto.TradeInfoClientDTO;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ExchangeParser {

    private static final String LEVEL1_URL = "http://localhost:8080/api/get?";

    private final ObjectMapper MAPPER = new ObjectMapper();

    public String getLastTradePrice(String ticker) {
        String jsonResponse;
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            CloseableHttpResponse httpResponse = client.execute(createRequest(ticker));
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new ParseException(
                        String.format("Unable to get response for ticker: %s", ticker));
            }
            String jsonString = EntityUtils.toString(httpResponse.getEntity());
            jsonResponse = MAPPER.writeValueAsString(MAPPER.readValue(jsonString, TradeInfoClientDTO.class));
        } catch (IOException e) {
            e.printStackTrace();
            jsonResponse = "";
        }
        return jsonResponse;
    }

    private HttpUriRequest createRequest(String ticker) {
        return new HttpGet(LEVEL1_URL + "ticker=" + ticker);
    }
}
