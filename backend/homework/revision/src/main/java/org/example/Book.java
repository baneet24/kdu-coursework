package org.example;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private String isbn;
    private boolean checkedOut;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private String genre;
    public Book(int id, String title, String isbn, boolean checkedOut, String genre) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.checkedOut = checkedOut;
        this.genre = genre;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
        this.returnDate = issueDate.plusDays(Constants.DAYS_TO_RETURN);
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
