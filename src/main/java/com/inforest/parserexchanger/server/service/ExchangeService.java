package com.inforest.parserexchanger.server.service;

import com.inforest.parserexchanger.server.model.response.TradeClientResponse;
import com.inforest.parserexchanger.server.model.response.TradeInfoClientResponse;
import com.inforest.parserexchanger.server.service.mapper.ResponseMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Service
public class ExchangeService {

    private final Properties property = new Properties();

    public TradeInfoClientResponse getLastTradePrice(String ticker) {
        ResponseMapper mapper = new ResponseMapper();
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            CloseableHttpResponse httpResponse = client.execute(createRequest(ticker));
            String jsonResponse = EntityUtils.toString(httpResponse.getEntity());
            mapper.initMapping(jsonResponse, ticker);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TradeInfoClientResponse(
                new TradeClientResponse(mapper.getTicker(), mapper.getTime(), mapper.getPrice()));
    }

    private HttpUriRequest createRequest(String ticker) throws IOException {
        property.load(new FileReader("src/main/resources/static/config.properties"));
        String address = property.getProperty("exchange.address");
        return new HttpGet(String.format(address + "%s/trades.json?limit=1&reversed=1", ticker));
    }
}
