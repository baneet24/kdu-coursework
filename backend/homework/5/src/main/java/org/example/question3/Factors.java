package org.example.question3;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Factors implements Runnable{
    int number;
    private static final Logger logger = Logger.getLogger(Factors.class.getName());

    public Factors(int number){
        this.number = number;
    }

    @Override
    public void run(){
        calculateFactors();
    }

    private void calculateFactors(){
        ArrayList<Integer> factors = new ArrayList<>();
        for(int i = 1; i*i < number; i++){
            if(number%i == 0){
                factors.add(i);
                if(number/i != i){
                    factors.add(number/i);
                }
            }
        }

        printFactors(factors);

    }

    void printFactors(ArrayList<Integer> factors){
        logger.log(Level.INFO,"Factors are: ");
        for(int factor: factors){
            logger.log(Level.INFO, factor+" ");

        }
    }

}
