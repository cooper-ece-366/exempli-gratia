package edu.cooper.ece366.project.borsa.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cooper.ece366.yahoofinance.api.Stock;
import edu.cooper.ece366.yahoofinance.api.YahooFinance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.Control;
import java.io.IOException;
import java.math.BigDecimal;

@RestController
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping(value = "/api")
public class ControllerApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerApi.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(path = "/version", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getVersion() {
        return(Version.getInstance().getVersionJSON());
    }

    @GetMapping(path = "/time", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSystemTime() throws JsonProcessingException {
        ApiTime theTime = new ApiTime();
        String timeString = objectMapper.writeValueAsString(theTime);
        return(timeString);
    }

    @GetMapping("/quote")
    public String getQuote() throws JsonProcessingException {
        ApiQuote quote = new ApiQuote();
        String quoteString = objectMapper.writeValueAsString(quote);
        return(quoteString);
    }

    @GetMapping(path = "/stock/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClosingStockPrice(@PathVariable final String ticker) throws JsonProcessingException {
        try {
            Stock stock = YahooFinance.get(ticker);
            BigDecimal price = stock.getQuote().getPrice();
            BigDecimal change = stock.getQuote().getChangeInPercent();
            BigDecimal peg = stock.getStats().getPeg();
            BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
            LOGGER.debug(stock.toString());
            String theStockJSON = objectMapper.writeValueAsString(stock);
            LOGGER.debug(theStockJSON);
            return(theStockJSON);
        }
        catch (IOException ex) {
            System.out.println("Unable to communicate to Yahoo Finance.");
            return("ex.toString()");
        }
    }
}
