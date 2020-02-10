package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void shouldReturnTheBookInputtedByTheCustomer() {//TODO: better name
        String input = "A\nRajesh\n2020";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Book InputtedBook = Book.getQueriedBook("A", "Rajesh", 2020);
        Book ExpectedBook = new Book("A", "Rajesh",2020);

        assertEquals(ExpectedBook, InputtedBook);
    }

    @Test
    void shouldGetInformationOfBook() {
        Book book = new Book("A", "Ramesh",2020);
        String expectedInfo = "A\tRamesh\t2020";
        assertEquals(expectedInfo, book.getInfo());
    }
}