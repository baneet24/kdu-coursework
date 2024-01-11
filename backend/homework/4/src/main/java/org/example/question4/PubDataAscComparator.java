package org.example.question4;
import java.util.Comparator;

/**
 * sorts books by publication year in ascending order
 */
public class PubDataAscComparator implements Comparator<Book> {
    public int compare(Book firstBook, Book secondBook) {
        int yearOfComparison = Integer.compare(firstBook.getYear(),secondBook.getYear());
        if(yearOfComparison != 0 ) {
            return yearOfComparison;
        }
        return firstBook.compareTo(secondBook);
    }
}