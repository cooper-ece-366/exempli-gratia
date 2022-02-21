package edu.cooper.ece366.project.borsa.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestApiServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiServer.class);

    public static void main(String[] args) {
        LOGGER.info(String.format("Application Version = %s", Version.APP_VERSION));
        SpringApplication.run(RestApiServer.class, args);
        LOGGER.info("Running RestApiServer.");
    }
}
