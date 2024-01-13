package org.example.question2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MessageQueue2 messageQueue = new MessageQueue2();

        ExecutorService senderThreadPool = Executors.newFixedThreadPool(3);
        ExecutorService receiverThreadPool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 3; i++) {
            senderThreadPool.submit(new MessageSender2(messageQueue, i));
        }

        for (int i = 1; i <= 3; i++) {
            receiverThreadPool.submit(new MessageReceiver2(messageQueue));
        }

        //to close or shut down the thread when the work is done
        senderThreadPool.shutdown();
        receiverThreadPool.shutdown();
    }
}