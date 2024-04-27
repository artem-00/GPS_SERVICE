package com.example.gps.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EndpointActionLogger {

    private final Logger logger = LoggerFactory.getLogger(EndpointActionLogger.class);

    public void logAddLocation(String message) {
        logger.info("Adding location: {}", message);
    }

    public void logDeleteLocation(Long id) {
        logger.info("Deleting location with id: {}", id);
    }

}
