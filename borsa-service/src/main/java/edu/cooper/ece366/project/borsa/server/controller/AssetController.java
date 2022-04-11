package edu.cooper.ece366.project.borsa.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cooper.ece366.project.borsa.server.exception.ResourceNotFoundException;
import edu.cooper.ece366.project.borsa.server.model.Asset;
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

import javax.sound.sampled.Port;
import java.util.List;

@RestController
@RequestMapping("/portfolios")
public class AssetController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssetController.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private AssetRepository assetRepository;

    @GetMapping("/portfolio/assets") // TODO wire up to database calls via User -> Portfolio -> Asset
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Asset>> getAssetsByUserId(@CurrentUser UserPrincipal userPrincipal)
            throws JsonProcessingException {
        Portfolio portfolio = portfolioRepository.findByUserId(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User","id",userPrincipal.getId()));
        List<Asset> assets = assetRepository.findByPortfolio(portfolio)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio","id",portfolio.getId()));
        return new ResponseEntity<List<Asset>>(assets, HttpStatus.OK);
    }

    @PostMapping("/portfolio/assets/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Asset> addAsset(@CurrentUser UserPrincipal userPrincipal,
                                          @RequestBody Asset assetRequest) {
//        User user = userRepository.findById(userPrincipal.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("User","id",userPrincipal.getId()));
        Long userId = userPrincipal.getId();
        Portfolio portfolio = portfolioRepository.findByUserId(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio","id",userId));
        assetRequest.setPortfolio(portfolio);
        Asset asset = assetRepository.save(assetRequest);
        return new ResponseEntity<Asset>(asset, HttpStatus.CREATED);
    }
}
