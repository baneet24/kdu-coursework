package org.example.question3;

import org.example.LoggerService;

import java.util.Scanner;

public class Main {
    private static final LoggerService logger = new LoggerService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Float[] intArray = {1.3f, 2.4f, 3.1f, 4.5f, 5.2f};
        logger.printLogInfo("Array before swapping: ");
        printArray(intArray);

        logger.printLogInfo("Enter indexes: ");
        int indexOne = scanner.nextInt();
        int indexTwo = scanner.nextInt();

        ExchangePositions exchangeFloatArr = new ExchangePositions();
        exchangeFloatArr.swap(intArray, indexOne, indexTwo);

        logger.printLogInfo("Array after swapping: ");
        printArray(intArray);


        String[] stringArray = {"Abc", "owl", "dog", "house"};
        logger.printLogInfo("\nOriginal String Array: ");
        printArray(stringArray);


        ExchangePositions exchangeStringArr = new ExchangePositions();
        exchangeStringArr.swap(stringArray, indexOne, indexTwo);

        logger.printLogInfo("Array after swapping: ");
        printArray(stringArray);
    }

    private static <T> void printArray(T[] array) {
        for (T element : array) {
            logger.printLogInfo(element + " ");
        }
    }
}
