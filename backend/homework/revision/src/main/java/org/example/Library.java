package org.example;

import org.example.exceptions.AuthorNotFoundException;
import org.example.exceptions.PatronNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Patron> patrons = new ArrayList<>();
    private final List<Book> checkedOutBooks = new ArrayList<>();

    public void addPatron(Patron patron){
        patrons.add(patron);
    }

    /**
     * @return List of books available in the library
     */
    public List<String> findAllAvailableBooks(){
        return books.stream().filter(book -> !book.isCheckedOut()).map(Book::getTitle).toList();
    }

    /**
     * this functions is used to borrow book from library
     * @param book
     * @param patron
     */
    public void checkOutBooks(Book book, Patron patron){
        if(!book.isCheckedOut()) {
            if (patrons.contains(patron)) {
                book.setCheckedOut(true);
                checkedOutBooks.add(book);
                patron.getCheckedOutBooks().add(book);
                book.setIssueDate(LocalDate.now().minusDays(Constants.SAMPLE_DAY));
            }
            else{
                throw new PatronNotFoundException("Patron not found");
            }
        }
        else{
            LoggerService.logError("Book is already checked out.");
        }
    }

    /**
     * @param author
     * @return list of books written by an author
     */
    public List<String> findBooksByAuthor(Author author) {
        if (author == null || !authors.contains(author)) {
            throw new AuthorNotFoundException("Author not found");
        }

        return author.getBooks().stream().map(Book::getTitle).toList();
    }

    /**
     * @return returns list of books that are not returned on due date
     */
    public List<Book> findOverDueBooks() {
        LocalDate currentDate = LocalDate.now();

        return checkedOutBooks.stream()
                .filter(Book::isCheckedOut)
                .filter(book -> book.getReturnDate().isBefore(currentDate)) .toList();
    }

    /**
     * @param topN
     * @return list of top n popular books
     */
    public List<String> findPopularBooks(int topN) {
      Map<String, Long> popularBooks = checkedOutBooks.stream().collect(Collectors.groupingBy(Book::getTitle, Collectors.counting()));

      return popularBooks.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
              .limit(topN).map(Map.Entry::getKey)
              .toList();
    }

    /**
     * function groups all the books corresponding to a genre together
     * @return map of books grouped by genre
     */
    public Map<String, List<Book>> groupBooksByGenre() {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getGenre));
    }

    public void addAuthor(Author author) {
        authors.add(author);
        books.addAll(author.getBooks());
    }
}
