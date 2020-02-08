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
    }
}
