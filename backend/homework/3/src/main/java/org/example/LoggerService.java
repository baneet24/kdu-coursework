package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerService {
    private static final Logger logger = LoggerFactory.getLogger(LoggerService.class);

    public void printLogInfo(String message) {
        logger.info(message);
    }

}
