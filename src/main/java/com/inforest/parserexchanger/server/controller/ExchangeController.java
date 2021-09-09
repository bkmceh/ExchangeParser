package com.inforest.parserexchanger.server.controller;

import com.inforest.parserexchanger.server.model.response.TradeInfoClientResponse;
import com.inforest.parserexchanger.server.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/get")
    public ResponseEntity<TradeInfoClientResponse> getLastTradePrice(@RequestParam(name = "ticker") String ticker) {
        return ResponseEntity.ok(exchangeService.getLastTradePrice(ticker));
    }
}
