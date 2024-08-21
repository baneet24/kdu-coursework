package org.example.question2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class MessageQueue2 {
    private final BlockingQueue<String> queue;

    public MessageQueue2() {
        this.queue = new LinkedBlockingQueue<>();
    }

    public void addMessage(String message) {
        try {
            queue.put(message);
            synchronized (this) {
                notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * method retrieves a message from the queue
     * while handling synchronization and interruptions
     * to ensure safe and reliable message retrieval.
     * @return
     */

    public String getMessage() {
        try {
            synchronized (this) {
                while (queue.isEmpty()) {
                    wait();
                }
            }
            return queue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

}

