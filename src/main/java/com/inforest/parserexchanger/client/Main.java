package com.inforest.parserexchanger.client;

import com.inforest.parserexchanger.client.exception.InvalidTickerValueException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExchangeParser exchangeParser = new ExchangeParser();
        String ticker = getTicker();
        String response = exchangeParser.getLastTradePrice(ticker);
        System.out.println(response);
    }
    public static String getTicker() {
        System.out.print("Write ticker: ");
        Scanner scanner = new Scanner(System.in);
        String ticker = scanner.nextLine().toLowerCase();
        if (ticker.length() < 1 || ticker.length() > 6) {
            throw new InvalidTickerValueException("Invalid ticker. Try again");
        }
        return ticker;
    }
}