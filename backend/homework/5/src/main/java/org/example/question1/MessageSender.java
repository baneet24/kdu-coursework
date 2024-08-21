package org.example.question1;


class MessageSender extends Thread {

    private final MessageQueue messageQueue;
    private final int senderId;

    public MessageSender(MessageQueue messageQueue, int senderId) {
        this.messageQueue = messageQueue;
        this.senderId = senderId;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            String message = "Sender with " + senderId + " sent message: " + i;
            messageQueue.addMessage(message);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
