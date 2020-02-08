package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();

    public Library() {
        books.add(new Book("A","Charles",2015));   // pre-existing books
        books.add(new Book("B","Henry",2017));
        books.add(new Book("C","Richard",2012));
    }

    public List<Book> getListOfBooks() {
        return books;
    }
}

