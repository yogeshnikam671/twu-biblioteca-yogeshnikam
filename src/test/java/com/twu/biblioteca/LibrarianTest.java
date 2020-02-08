package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {
    @Test
    void shouldBeAbleToPrepareACheckedOutBookForCollection() {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Book book = new Book("C", "Richard", 2012);

        library.checkOut(book);

        assertTrue(librarian.getCheckedOutBooks().contains(book));
    }

    @Test
    void shouldBeAbleToPrepareAListOfCheckedOutBooksForCollection() {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Book book1 = new Book("C", "Richard", 2012);
        Book book2 = new Book("A", "Charles", 2015);
        List<Book> checkedOutBooks = new ArrayList<>(asList(book1, book2));

        library.checkOut(book1);
        library.checkOut(book2);


        assertEquals(checkedOutBooks, librarian.getCheckedOutBooks());
    }

    @Test
    void shouldNotifyCustomerOnSuccessfulCheckoutOfTheBook() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Book book = new Book("C", "Richard", 2012);
        String expectedMessage = "Thank You! Enjoy the book";

        library.checkOut(book);

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldNotifyCustomerOnUnSuccessfulCheckoutOfTheBook() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Book book = new Book("D", "Rajesh", 2012);
        String expectedMessage = "Sorry, that book is not available";

        library.checkOut(book);

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldRemoveTheBookFromCheckedOutListWhenItIsReturned() {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Book book = new Book("C", "Richard", 2012);

        library.checkOut(book);
        library.returnBack(book);

        assertFalse(librarian.getCheckedOutBooks().contains(book));
    }

    @Test
    void shouldBeAbleToCheckIfTheCustomerIsReturningAValidBook() {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Book book = new Book("C", "Richard", 2012);

        assertTrue(librarian.isValid(book));
    }

    @Test
    void shouldBeAbleToCheckIfTheCustomerIsReturningAnInValidBook() {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Book book = new Book("D", "Rajesh", 2012);

        assertFalse(librarian.isValid(book));
    }
}