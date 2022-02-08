package edu.cooper.ece366.project.borsa.server;

import edu.cooper.ece366.yahoofinance.api.YahooFinance;
import edu.cooper.ece366.yahoofinance.api.Stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        logger.info("Example log from {}", Server.class.getSimpleName());
        System.out.println("The Server says hi!");

        try {
            Stock stock = YahooFinance.get("INTC");
            BigDecimal price = stock.getQuote().getPrice();
            BigDecimal change = stock.getQuote().getChangeInPercent();
            BigDecimal peg = stock.getStats().getPeg();
            BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
            stock.print();

            stock = YahooFinance.get("AAPL");
            stock.print();

        }
        catch (IOException ex) {
            System.out.println("Unable to communicate to Yahoo Finance.");
        }
    }
}
