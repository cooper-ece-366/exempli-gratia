package edu.cooper.ece366.project.borsa.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cooper.ece366.project.borsa.server.model.ApiQuote;
import edu.cooper.ece366.project.borsa.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
public class UserPortfolioController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPortfolioController.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/tickers") // TODO wire up to database calls via UserRepository
    public String getTickers() throws JsonProcessingException {
        String tickers = "{\"tickers\": [\"AAPL\",\"AMZN\",\"INTC\",\"NVDA\"]}";
//        String tickersCSV = objectMapper.writeValueAsString(tickers);
        return(tickers);
    }
}
