package edu.cooper.ece366.project.borsa.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cooper.ece366.project.borsa.server.exception.ResourceNotFoundException;
import edu.cooper.ece366.project.borsa.server.model.Portfolio;
import edu.cooper.ece366.project.borsa.server.model.User;
import edu.cooper.ece366.project.borsa.server.repository.AssetRepository;
import edu.cooper.ece366.project.borsa.server.repository.PortfolioRepository;
import edu.cooper.ece366.project.borsa.server.repository.UserRepository;
import edu.cooper.ece366.project.borsa.server.security.CurrentUser;
import edu.cooper.ece366.project.borsa.server.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PortfolioController.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private AssetRepository assetRepository;

    @GetMapping("/portfolio/{id}") // TODO wire up to database calls via User -> Portfolio -> Asset
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Portfolio> getPortfolioByUserId(@PathVariable(value = "id") Long id)
            throws JsonProcessingException {
        Portfolio portfolio = portfolioRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio", "id", id));
        return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Portfolio> createPortfolio(@CurrentUser UserPrincipal userPrincipal,
                                                     @RequestBody Portfolio portfolioRequest) {
        Portfolio portfolio = userRepository.findById(userPrincipal.getId())
                .map(user -> { // TODO need to check if User has a portfolio already
                    portfolioRequest.setUser(user);
                    return portfolioRepository.save(portfolioRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("User","id",userPrincipal.getId()));
        return new ResponseEntity<>(portfolio, HttpStatus.CREATED);
    }

//    @PostMapping("/ticker") // TODO
//    @PreAuthorize("hasRole('USER')")
//    public User newAsset(@CurrentUser UserPrincipal userPrincipal, @RequestBody String newAsset) {
//        LOGGER.info(String.format("userPrincipal = %s; Asset = %s", userPrincipal.toString(), newAsset));
//        User me = userRepository.findById(userPrincipal.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
////        Portfolio portfolio = me.getPortfolio();
//////        portfolioRepository.save(portfolio);
////        Asset theAsset = new Asset(newAsset);
////        portfolio.addAsset(theAsset);
//        return(me);
//    }
}
