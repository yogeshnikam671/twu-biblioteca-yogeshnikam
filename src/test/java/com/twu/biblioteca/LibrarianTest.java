package com.twu.biblioteca;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LibrarianTest {

    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void revertStreams() {
        System.setOut(sysOut);
    }

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
        Librarian librarian = new Librarian();
        String expectedMessage = "Thank You! Enjoy the book\n";

        librarian.notifyAsSuccessfulCheckout();

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldNotifyCustomerOnUnSuccessfulCheckoutOfTheBook() {
        Librarian librarian = new Librarian();
        String expectedMessage = "Sorry, that book is not available\n";

        librarian.notifyAsUnsuccessfulCheckOut();

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

        library.checkOut(book);

        assertTrue(librarian.isCheckedOut(book));
    }

    @Test
    void shouldBeAbleToCheckIfTheCustomerIsReturningAnInValidBook() {
        Librarian librarian = new Librarian();
        Book book = new Book("D", "Rajesh", 2012);

        assertFalse(librarian.isCheckedOut(book));
    }

    @Test
    void shouldNotifyCustomerOnSuccessfulReturnOfTheBook() {
        Librarian librarian = new Librarian();
        String expectedMessage = "Thank you for returning the book\n";

        librarian.notifyAsSuccessfulReturn();

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldNotifyCustomerOnUnSuccessfulReturnOfTheBook() {
        Librarian librarian = new Librarian();
        String expectedMessage = "This is not a valid book to return\n";

        librarian.notifyAsUnsuccessfulReturn();

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldAddTheBookToCheckedOutListWhenMarkedAsCheckedOut() {
        Librarian librarian = new Librarian();
        Book book = mock(Book.class);

        librarian.markAsCheckedOut(book);

        assertTrue(librarian.getCheckedOutBooks().contains(book));
    }

    @Test
    void shouldRemoveTheBookFromCheckedOutListWhenMarkedAsReturned() {
        Librarian librarian = new Librarian();
        Book book = mock(Book.class);

        librarian.markAsReturned(book);

        assertFalse(librarian.getCheckedOutBooks().contains(book));
    }
}