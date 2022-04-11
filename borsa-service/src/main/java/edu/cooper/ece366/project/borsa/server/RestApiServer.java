package edu.cooper.ece366.project.borsa.server;

import edu.cooper.ece366.project.borsa.server.config.AppProperties;
import edu.cooper.ece366.project.borsa.server.model.Asset;
import edu.cooper.ece366.project.borsa.server.model.AuthProvider;
import edu.cooper.ece366.project.borsa.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

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

}
