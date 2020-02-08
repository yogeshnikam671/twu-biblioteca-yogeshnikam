package com.twu.biblioteca;

public class Librarian {
    private Book checkedOutBook;

    public Librarian() {
    }

    public Book getCheckedOutBook() {
        return checkedOutBook;
    }

    public void markAsCheckedOut(Book book) {
        checkedOutBook = book;
    }
}
