package org.example;

import org.example.exceptions.AuthorNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        Author author1 = new Author("Author1", Arrays.asList(
                new Book(1, "Book1", "ISBN1", false, "Genre1"),
                new Book(2, "Book2", "ISBN2", false, "Genre2")
        ));

        Author author2 = new Author("Author2", Arrays.asList(
                new Book(3, "Book3", "ISBN3", false, "Genre1"),
                new Book(4, "Book4", "ISBN4", false, "Genre2")
        ));

        library.addAuthor(author1);
        library.addAuthor(author2);


        Book book1 = author1.getBooks().get(0);
        List<Book> checkedOutBooks = new ArrayList<>();

        Patron patron = new Patron("John Doe", Constants.USER_CHECKOUT_LIMIT, checkedOutBooks);

        library.addPatron(patron);

        library.checkOutBooks(book1, patron);

        LoggerService.logInfo("Available Books: " + library.findAllAvailableBooks());

        try {
            LoggerService.logInfo("Books by Author1: " + library.findBooksByAuthor(author1));
        } catch (AuthorNotFoundException e) {
            LoggerService.logError(e.getMessage());
        }

        List<Book> overdueBooks = library.findOverDueBooks();
        LoggerService.logInfo("Overdue Books: " + overdueBooks);

        List<String> popularBooks = library.findPopularBooks(Constants.TOP_N_BOOKS);
        LoggerService.logInfo("Top 2 Popular Books: " + popularBooks);

        Map<String, List<Book>> booksByGenre = library.groupBooksByGenre();

        for (Map.Entry<String, List<Book>> entry : booksByGenre.entrySet()) {
            String genre = entry.getKey();
            List<Book> books = entry.getValue();

            LoggerService.logInfo("Genre: " + genre);
            LoggerService.logInfo("Books: " + books);
        }
    }
}
