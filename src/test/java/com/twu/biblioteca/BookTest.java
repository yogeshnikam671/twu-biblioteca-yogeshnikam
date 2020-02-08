package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void shouldBeAbleToReturnOnlyItsTitle() {
        Book book = new Book("A","Rajesh",1995);
        assertEquals("A", book.getTitle());
    }

    @Test
    void shouldReturnTheBookInputtedByTheCustomer() {
        String input = "A\nRajesh\n2020";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Book InputtedBook = Book.getInputtedBook();
        Book ExpectedBook = new Book("A", "Rajesh",2020);

        assertEquals(ExpectedBook, InputtedBook);
    }
}