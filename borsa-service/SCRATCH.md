# SCRATCH.md

UserPortfolio
```java
package edu.cooper.ece366.project.borsa.server.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "portfolios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "userId")
})
public class UserPortfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = true)
    private String tickers; // TODO for now, have list of tickers stored common-separated

    public UserPortfolio() {};

    public UserPortfolio(String tickers) {
        this.tickers = tickers;
    }

    @Override
    public String toString() {
        return "UserPortfolio{" +
                "id=" + id +
                ", userId=" + userId +
                ", tickers='" + tickers + '\'' +
                '}';
    }
}

```

UserPortfolioRepository

```java
package edu.cooper.ece366.project.borsa.server.repository;

import edu.cooper.ece366.project.borsa.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPortfolioRepository extends JpaRepository<UserPortfolio, Long> {
    Optional<UserPortfolio> findUserById(Long id);

}

```

UserPortfolioService

```java
package edu.cooper.ece366.project.borsa.server.services;

import edu.cooper.ece366.project.borsa.server.model.User;
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

```
RestApiServer

```java
package edu.cooper.ece366.project.borsa.server;

import edu.cooper.ece366.project.borsa.server.config.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = {
        "edu.cooper.ece366.project.borsa.server"
})
@EnableConfigurationProperties(AppProperties.class)
public class RestApiServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiServer.class);

    public static void main(String[] args) {
        LOGGER.info(String.format("Application Version = %s", Version.APP_VERSION));
        SpringApplication.run(RestApiServer.class, args);
        LOGGER.info("Running RestApiServer.");
    }

    @Bean
    public CommandLineRunner demo(UserPortfolioRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new UserPortfolio("AMZN,APPL,INTC"));

            // fetch all customers
            LOGGER.info("User Portfolios found with findAll():");
            LOGGER.info("-------------------------------");
            for (UserPortfolio customer : repository.findAll()) {
                LOGGER.info(customer.toString());
            }
            LOGGER.info("");

            // fetch an individual customer by ID
            Optional<UserPortfolio> userPortfolio = repository.findById(1L);
            LOGGER.info("Customer found with findById(1L):");
            LOGGER.info("--------------------------------");
            LOGGER.info(userPortfolio.toString());
            LOGGER.info("");
        };
    }

}

```