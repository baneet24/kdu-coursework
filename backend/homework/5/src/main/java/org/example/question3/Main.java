package org.example.question3;


import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) throws InterruptedException {

        int targetNumber = 6;

        Factors factors = new Factors(targetNumber);
        Thread factorThread = new Thread(factors);

        Factorial factorial = new Factorial(targetNumber);
        Thread factorialThread= new Thread(factorial);

        factorThread.start();
        factorialThread.start();

        factorThread.join();
        factorialThread.join();

        logger.info("Main thread finished.");
    }
}
