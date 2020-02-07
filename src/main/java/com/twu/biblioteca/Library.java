package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();

    public Library() {
        books.add(new Book("A"));   // pre-existing books
        books.add(new Book("B"));
        books.add(new Book("C"));
    }

    public List<Book> getListOfBooks() {
        return books;
    }
}
