package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void shouldBeAbleToShowListOfBooks() {
        Library library = new Library();
        List<Book> books = new ArrayList<>();
        books.add(new Book("A"));
        books.add(new Book("B"));
        books.add(new Book("C"));

        assertEquals(books, library.getListOfBooks());
    }
}