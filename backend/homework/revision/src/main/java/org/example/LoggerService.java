package org.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerService {
    public static final Logger log = LoggerFactory.getLogger(LoggerService.class);

    public static void logTrace(String message) {
        log.trace(message);
    }

    public static void logDebug(String message) {
        log.debug(message);
    }

    public static void logInfo(String message) {
        log.info(message);
    }

    public static void logWarn(String message) {
        log.warn(message);
    }

    public static void logError(String message) {
        log.error(message);
    }
}
