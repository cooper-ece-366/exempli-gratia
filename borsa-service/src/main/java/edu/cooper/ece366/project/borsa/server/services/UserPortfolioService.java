package edu.cooper.ece366.project.borsa.server.services;

import edu.cooper.ece366.project.borsa.server.model.User;
import edu.cooper.ece366.project.borsa.server.model.UserPortfolio;
import edu.cooper.ece366.project.borsa.server.repository.UserPortfolioRepository;
import edu.cooper.ece366.project.borsa.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserPortfolioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPortfolioService.class);

    @Autowired
    private UserPortfolioRepository userPortfolioRepository;

    public List<UserPortfolio> listAllUserPortfolioers() {
        List<UserPortfolio> listOfAllUserPortfolios = userPortfolioRepository.findAll();
        LOGGER.info(String.format("all user portfolios = %s", listOfAllUserPortfolios.toString()));
        return listOfAllUserPortfolios;
    }

}
