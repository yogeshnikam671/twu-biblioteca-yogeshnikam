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
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Library library = new Library();
        List<Book> books = new ArrayList<>();
        books.add(new Book("A","Charles",2015));
        books.add(new Book("B","Henry",2017));
        books.add(new Book("C","Richard",2012));

        library.showBooks();

        assertEquals(books, library.getBooks());
        assertEquals("1. A\n2. B\n3. C\n", outContent.toString());
    }
}