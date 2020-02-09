package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    private List<Book> checkedOutBooks;
    private final static String SUCCESS_CHECKOUT_MESSAGE = "Thank You! Enjoy the book";
    private final static String UNSUCCESS_CHECKOUT_MESSAGE = "Sorry, that book is not available";
    private final static String SUCCESS_RETURN_MESSAGE = "Thank you for returning the book";
    private final static String UNSUCCESS_RETURN_MESSAGE = "This is not a valid book to return";

    public Librarian() {
        checkedOutBooks = new ArrayList<>();
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void markAsCheckedOut(Book book) {
        checkedOutBooks.add(book);
        System.out.print(SUCCESS_CHECKOUT_MESSAGE);
    }

    public void notifyAsUnsuccessfulCheckOut() {
        System.out.print(UNSUCCESS_CHECKOUT_MESSAGE);
    }

    public void markAsReturned(Book book) {
        checkedOutBooks.remove(book);
        System.out.print(SUCCESS_RETURN_MESSAGE);
    }

    public boolean isValid(Book book) {
        return checkedOutBooks.contains(book);
    }

    public void notifyAsUnsuccessfulReturn() {
        System.out.print(UNSUCCESS_RETURN_MESSAGE);
    }
}
