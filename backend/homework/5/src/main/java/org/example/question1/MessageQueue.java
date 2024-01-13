package org.example.question1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class MessageQueue {
    private final BlockingQueue<String> messagingQueue = new LinkedBlockingQueue<>();

    public void addMessage(String message) {
        try {
            messagingQueue.put(message);
            synchronized (this) {
                notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getMessage() {
        try {
            synchronized (this) {
                while (messagingQueue.isEmpty()) {
                    wait();
                }
            }
            return messagingQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
}
