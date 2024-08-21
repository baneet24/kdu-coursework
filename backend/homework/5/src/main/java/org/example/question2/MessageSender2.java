package org.example.question2;

class MessageSender2 implements Runnable {
    private final MessageQueue2 messageQueue;
    private final int senderId;


    public MessageSender2(MessageQueue2 messageQueue, int senderId) {
        this.messageQueue = messageQueue;
        this.senderId = senderId;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            String message = "Message from Sender " + senderId + ": " + i;
            messageQueue.addMessage(message);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}


