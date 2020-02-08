package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void shouldBeAbleToReturnOnlyItsTitle() {
        Book book = new Book("A","Rajesh",1995);
        assertEquals("A", book.getTitle());
    }
}