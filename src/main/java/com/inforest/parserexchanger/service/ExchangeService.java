package com.inforest.parserexchanger.service;

import com.inforest.parserexchanger.exception.TickerParseException;
import com.inforest.parserexchanger.model.response.TradeClientResponse;
import com.inforest.parserexchanger.model.response.TradeInfoClientResponse;
import com.inforest.parserexchanger.service.mapper.ResponseMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ExchangeService {

    @Value("${exchange.address}")
    private String exchangeURL;

    public TradeInfoClientResponse getLastTradePrice(String ticker) {
        ResponseMapper mapper = new ResponseMapper();
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            CloseableHttpResponse httpResponse = client.execute(createRequest(ticker));
            String jsonResponse = EntityUtils.toString(httpResponse.getEntity());
            mapper.initMapping(jsonResponse, ticker);
        } catch (IOException e) {
            e.printStackTrace();
            throw new TickerParseException("Unable to get data of ticker: " + ticker);
        }
        return new TradeInfoClientResponse(
                new TradeClientResponse(mapper.getTicker(), mapper.getTime(), mapper.getPrice()));
    }

    private HttpUriRequest createRequest(String ticker) throws IOException {
        return new HttpGet(String.format(exchangeURL + "%s/trades.json?limit=1&reversed=1", ticker));
    }
}
