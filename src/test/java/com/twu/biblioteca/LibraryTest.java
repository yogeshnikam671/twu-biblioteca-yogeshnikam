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
        books.add(new Book("A", "Charles", 2015));
        books.add(new Book("B", "Henry", 2017));
        books.add(new Book("C", "Richard", 2012));

        library.showBooks();

        assertEquals(books, library.getBooks());
        assertEquals("1. A\tCharles\t2015\n2. B\tHenry\t2017\n3. C\tRichard\t2012\n", outContent.toString());
    }

    @Test
    void shouldAllowTheCustomerToCheckOutABook() {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);

        List<Book> books = new ArrayList<>();
        books.add(new Book("A", "Charles", 2015));
        books.add(new Book("B", "Henry", 2017));

        Book book = new Book("C", "Richard", 2012);

        library.checkOut(book);

        assertEquals(books, library.getBooks());
    }

    @Test
    void shouldAllowTheCustomerToReturnABook() {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Book book = new Book("C", "Richard", 2012);

        library.checkOut(book);
        library.returnBack(book);

        assertTrue(library.getBooks().contains(book));
    }

}