package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {
    @Test
    void shouldBeAbleToPrepareACheckedOutBookForCollection() {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Book book = new Book("C", "Richard", 2012);

        library.checkOut(book);

        assertEquals(book, librarian.getCheckedOutBook());
    }
}