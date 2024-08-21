package org.example.question3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Factorial implements Runnable{
    int number;
    long factorialNumber;
    private static final Logger logger = Logger.getLogger(Factorial.class.getName());

    public Factorial(int number){
        this.number = number;
    }

    @Override
    public void run(){
        calculateFactorial();
    }

    private void calculateFactorial(){
        factorialNumber = 1;
        logger.log(Level.INFO, "Factorial is: ");
        for(int i = 2; i <= number; i++){
            factorialNumber = i*factorialNumber;
        }

         logger.log(Level.INFO, String.valueOf(factorialNumber));
    }
}
