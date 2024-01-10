package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class loggerService {
    private static final Logger logger = LoggerFactory.getLogger(loggerService.class);

    public void printLogInfo(String message) {
        logger.info(message);
    }

}
