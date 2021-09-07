package com.inforest.parserexchanger.client;

import com.inforest.parserexchanger.server.exception.InvalidTickerValueException;

import java.util.Scanner;

public class Main {

    private static final String LEVEL1_URL = "http://localhost/api/get/";

    public static void main(String[] args) {
        ExchangeParser exchangeParser = new ExchangeParser();
        System.out.print("Write ticker: ");
        Scanner scanner = new Scanner(System.in);
        String ticker = scanner.nextLine().toLowerCase();
        String response = exchangeParser.getLastTradePrice(createResponse(ticker));
        System.out.println(response);
    }

    private static String createResponse(String ticker) {
        if (ticker.length() < 1 || ticker.length() > 6) {
            throw new InvalidTickerValueException("Invalid ticker. Try again");
        }
        return LEVEL1_URL + ticker;
    }
}