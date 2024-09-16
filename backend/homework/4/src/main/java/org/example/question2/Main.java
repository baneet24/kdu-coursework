package org.example.question2;

import org.example.LoggerService;

import java.util.Scanner;

public class Main {

    private static final TicketReservation ticketReservation = new TicketReservation();
    private static final Scanner scanner = new Scanner(System.in);
    private static final LoggerService logger = new LoggerService();

    public static void main(String[] args) {
        bookFlight();
        cancelReservation();
    }
 

    private static void bookFlight() {
        scanner.nextLine();

        logger.printLogInfo("Enter first name: ");
        String firstName = scanner.nextLine();

        logger.printLogInfo("Enter last name: ");
        String lastName = scanner.nextLine();

        logger.printLogInfo("Enter age: ");
        int age = scanner.nextInt();

        scanner.nextLine();

        logger.printLogInfo("Enter gender: ");
        String gender = scanner.nextLine();

        logger.printLogInfo("Enter travel class (business/economy): ");
        String travelClass = scanner.nextLine();

        logger.printLogInfo("Enter confirmation number: ");
        String confirmationNumber = scanner.nextLine();

        boolean booked = ticketReservation.bookFlight(firstName, lastName, age, gender, travelClass, confirmationNumber);
        if (booked) {
            logger.printLogInfo("Booking successful!");
        } else {
            logger.printLogInfo("Booking failed. Both confirmedList and waitingList are full.");
        }
    }

    private static void cancelReservation() {
        scanner.nextLine();

        logger.printLogInfo("Enter confirmation number to cancel: ");
        String cancelConfirmationNumber = scanner.nextLine();

        boolean canceled = ticketReservation.cancel(cancelConfirmationNumber);
        if (canceled) {
            logger.printLogInfo("Cancellation successful!");
        } else {
            logger.printLogInfo("Passenger not found in confirmedList or waitingList.");
        }
    }

}
