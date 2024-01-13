package org.example.question1;


public class Main {
    public static void main(String[] args) {
        MessageQueue queueMsg = new MessageQueue();
        int length = 3;

        MessageSender[] senderArray = new MessageSender[length];
        MessageReceiver[] receiverArray = new MessageReceiver[length];

        for (int i = 0; i < 3; i++) {
            senderArray[i] = new MessageSender(queueMsg, i);
            receiverArray[i] = new MessageReceiver(queueMsg, i);
        }

        for (MessageSender sender : senderArray) {
            sender.start();
        }

        for (MessageReceiver receiver : receiverArray) {
            receiver.start();
        }
    }
}