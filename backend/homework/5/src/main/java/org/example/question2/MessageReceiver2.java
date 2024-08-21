package org.example.question2;

import java.util.logging.Level;
import java.util.logging.Logger;

class MessageReceiver2 implements Runnable {
    private static final Logger logger = Logger.getLogger(MessageReceiver2.class.getName());

    private final MessageQueue2 messageQueue;

    public MessageReceiver2(MessageQueue2 messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String message = messageQueue.getMessage();
                if (message != null) {
                    logger.log(Level.INFO, "Receiver received: {0}", message);
                }
            }
        } finally {
            logger.log(Level.INFO, "Receiver thread finished.");
        }
    }
}
