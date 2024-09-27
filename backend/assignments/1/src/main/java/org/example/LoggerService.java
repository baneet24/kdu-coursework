package org.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerService{
    private static Logger log = LoggerFactory.getLogger(LoggerService.class);

    public void logTrace(String message) {
        log.trace(message);
    }

    public void logDebug(String message) {
        log.debug(message);
    }

    public void logInfo(String message) {
        log.info(message);
    }

    public void logWarn(String message) {
        log.warn(message);
    }

    public void logError(String message) {
        log.error(message);
    }


}