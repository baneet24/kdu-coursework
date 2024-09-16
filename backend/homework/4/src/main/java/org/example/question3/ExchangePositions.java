package org.example.question3;

/**
 * swap elements in array of any type
 */
public class ExchangePositions {
    public <T> void swap(T[] a, int indexOne, int indexTwo){
       T tempNumber = a[indexOne];
       a[indexOne] = a[indexTwo];
       a[indexTwo] = tempNumber;
    }

}



