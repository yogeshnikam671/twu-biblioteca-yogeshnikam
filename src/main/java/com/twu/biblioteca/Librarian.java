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
    } //TODO: do you need this ?

    public void markAsCheckedOut(Book book) { //TODO: no tests, method name does not reveal the intention
        checkedOutBooks.add(book);
        notifyAsSuccessfulCheckout();
    }

    public void markAsReturned(Book book) { //TODO: no tests, method name does not reveal the intention
        checkedOutBooks.remove(book);
        notifyAsSuccessfulReturn();
    }

    public boolean isValid(Book book) {  // TODO: name
        return checkedOutBooks.contains(book);
    }

    public void notifyAsUnsuccessfulCheckOut() {
        System.out.print(UNSUCCESS_CHECKOUT_MESSAGE);
    } // TODO: no tests

    public void notifyAsUnsuccessfulReturn() {
        System.out.print(UNSUCCESS_RETURN_MESSAGE);
    }  // TODO: no tests

    private void notifyAsSuccessfulCheckout() {
        System.out.print(SUCCESS_CHECKOUT_MESSAGE);
    } // TODO: think of whos responsibility is printing or displaying

    private void notifyAsSuccessfulReturn() {
        System.out.print(SUCCESS_RETURN_MESSAGE);
    }

}
