package com.twu.biblioteca;

import com.twu.items.Book;
import com.twu.items.ItemType;
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
    User user = new User("123-4567", "dada");
    Printer printer = new Printer();


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
        Librarian librarian = new Librarian(printer);
        Library library = new Library(librarian, printer, new DataInitializer());
        Book book = new Book("C", "Richard", "2012");

        library.checkOut(book, ItemType.BOOK, user);

        assertTrue(librarian.getCheckedOut(ItemType.BOOK).contains(book));
    }

    @Test
    void shouldBeAbleToPrepareAListOfCheckedOutBooksForCollection() {
        Librarian librarian = new Librarian(printer);
        Library library = new Library(librarian, printer, new DataInitializer());
        Book book1 = new Book("C", "Richard", "2012");
        Book book2 = new Book("A", "Charles", "2015");
        List<Book> checkedOutBooks = new ArrayList<>(asList(book1, book2));

        library.checkOut(book1, ItemType.BOOK, user);
        library.checkOut(book2, ItemType.BOOK, user);


        assertEquals(checkedOutBooks, librarian.getCheckedOut(ItemType.BOOK));
    }

    @Test
    void shouldNotifyCustomerOnSuccessfulCheckoutOfTheBook() {
        Librarian librarian = new Librarian(printer);
        String expectedMessage = "Thank You! Enjoy the book\n\n";

        librarian.notifyAsSuccessfulCheckout();

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldNotifyCustomerOnUnSuccessfulCheckoutOfTheBook() {
        Librarian librarian = new Librarian(printer);
        String expectedMessage = "Sorry, that book is not available\n\n";

        librarian.notifyAsUnsuccessfulCheckOut();

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldRemoveTheBookFromCheckedOutListWhenItIsReturned() {
        Librarian librarian = new Librarian(printer);
        Library library = new Library(librarian, printer, new DataInitializer());
        Book book = new Book("C", "Richard", "2012");

        library.checkOut(book, ItemType.BOOK,user);
        library.returnBack(book,ItemType.BOOK,user);

        assertFalse(librarian.getCheckedOut(ItemType.BOOK).contains(book));
    }

    @Test
    void shouldBeAbleToCheckIfTheCustomerIsReturningAValidBook() {
        Librarian librarian = new Librarian(printer);
        Library library = new Library(librarian, printer, new DataInitializer());
        Book book = new Book("C", "Richard", "2012");

        library.checkOut(book, ItemType.BOOK, user);

        assertTrue(librarian.isCheckedOut(book, ItemType.BOOK, user));
    }

    @Test
    void shouldBeAbleToCheckIfTheCustomerIsReturningAnInValidBook() {
        Librarian librarian = new Librarian(printer);
        Book book = new Book("D", "Rajesh", "2012");

        assertFalse(librarian.isCheckedOut(book, ItemType.BOOK, user));
    }

    @Test
    void shouldNotifyCustomerOnSuccessfulReturnOfTheBook() {
        Librarian librarian = new Librarian(printer);
        String expectedMessage = "Thank you for returning the book\n\n";

        librarian.notifyAsSuccessfulReturn();

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldNotifyCustomerOnUnSuccessfulReturnOfTheBook() {
        Librarian librarian = new Librarian(printer);
        String expectedMessage = "This is not a valid book to return\n\n";

        librarian.notifyAsUnsuccessfulReturn();

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldAddTheBookToCheckedOutListWhenMarkedAsCheckedOut() {
        Librarian librarian = new Librarian(printer);
        Book book = mock(Book.class);

        librarian.markAsCheckedOut(book, ItemType.BOOK, user);

        assertTrue(librarian.getCheckedOut(ItemType.BOOK).contains(book));
    }

    @Test
    void shouldRemoveTheBookFromCheckedOutListWhenMarkedAsReturned() {
        Librarian librarian = new Librarian(printer);
        Book book = mock(Book.class);

        librarian.markAsReturned(book, ItemType.BOOK, user);

        assertFalse(librarian.getCheckedOut(ItemType.BOOK).contains(book));
    }

    @Test
    void shouldBeAbleToTellIfUserIsAccountableToReturnABookOrNot() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        User user1 = new User("123-4567", "dada");
        User user2 = new User("123-4568", "dada");

        Book book = new Book("A", "Charles", "2015");
        Librarian librarian = new Librarian(printer);
        Library library = new Library(librarian, printer, new DataInitializer());

        library.checkOut(book, ItemType.BOOK, user1);
        library.returnBack(book, ItemType.BOOK, user2);

        assertEquals("Thank You! Enjoy the book\n\nInvalid Return Request\n\n", outContent.toString());
    }

    @Test
    void shouldBeAbleToReturnTheListOfBooksCheckedOutByAnUser() {
        User user = new User("123-4567", "dada");
        Librarian librarian = new Librarian(printer);
        Library library = new Library(librarian, printer, new DataInitializer());
        Book book1 = new Book("A", "Charles", "2015");
        Book book2 = new Book("C", "Richard", "2012");

        library.checkOut(book1, ItemType.BOOK, user);
        library.checkOut(book2, ItemType.BOOK, user);


        assertEquals(asList(book1, book2), librarian.getBooksCheckedOutBy(user));
    }
}