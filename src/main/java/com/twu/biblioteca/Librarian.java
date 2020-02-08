package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    private List<Book> checkedOutBooks;

    public Librarian() {
        checkedOutBooks = new ArrayList<>();
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void markAsCheckedOut(Book book) {
        checkedOutBooks.add(book);
        System.out.print("Thank You! Enjoy the book");
    }

    public void notifyAsUnsuccessfulCheckOut(){
        System.out.print("Sorry, that book is not available");
    }


    public void markAsReturned(Book book) {
        checkedOutBooks.remove(book);
        System.out.print("Thank you for returning the book");
    }

    public boolean isValid(Book book) {
        return checkedOutBooks.contains(book);
    }

    public void notifyAsUnsuccessfulReturn() {
        System.out.print("This is not a valid book to return");
    }
}
