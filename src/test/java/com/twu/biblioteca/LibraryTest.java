package com.twu.biblioteca;

import com.twu.items.Book;
import com.twu.items.ItemType;
import com.twu.items.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LibraryTest {
    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Printer printer = new Printer();
    User user = new User("123-4567", "dada");


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
    void shouldBeAbleToShowListOfBooks() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Printer printer = new Printer();

        List<Book> books = new ArrayList<>();
        books.add(new Book("A", "Charles", "2015"));
        books.add(new Book("B", "Henry", "2017"));
        books.add(new Book("C", "Richard", "2012"));

        Library library = new Library(printer);


        library.show(ItemType.BOOK);

        assertEquals(books, library.get(ItemType.BOOK));
        assertEquals("1. A\tCharles\t2015\n2. B\tHenry\t2017\n3. C\tRichard\t2012\n", outContent.toString());
    }

    @Test
    void shouldAllowTheCustomerToCheckOutABook() {
        Printer printer = new Printer();
        Library library = new Library(printer);

        List<Book> books = new ArrayList<>();
        books.add(new Book("A", "Charles", "2015"));
        books.add(new Book("B", "Henry", "2017"));

        Book book = new Book("C", "Richard", "2012");

        library.checkOut(book, ItemType.BOOK, user);

        assertEquals(books, library.get(ItemType.BOOK));
    }

    @Test
    void shouldAllowTheCustomerToReturnABook() {
        Printer printer = new Printer();
        Library library = new Library(printer);
        Book book = new Book("C", "Richard", "2012");

        library.checkOut(book, ItemType.BOOK, user);
        library.returnBack(book, ItemType.BOOK, user);

        assertTrue(library.get(ItemType.BOOK).contains(book));
    }

    @Test
    void shouldBeAbleToShowListOfMovies() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Printer printer = new Printer();

        Library library = new Library(printer);

        library.show(ItemType.MOVIE);

       assertEquals("1. Dabangg\t2015\tRajesh\t10\n2. Bharat\t2019\tSuresh\t1\n", outContent.toString());
    }

    @Test
    void shouldAllowTheCustomerToCheckOutAMovie() {
        Printer printer = new Printer();
        Library library = new Library(printer);


        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Dabangg","2015", "Rajesh","10" ));

        Movie movie = new Movie("Bharat","2019","Suresh","1");

        library.checkOut(movie, ItemType.MOVIE);

        assertEquals(movies, library.get(ItemType.MOVIE));
    }

    @Test
    void shouldBeAbleToCheckIfTheUserIsValid() {
        Printer printer = new Printer();
        Library library = new Library(printer);
        User user = new User("123-4567","dada");

        assertTrue(library.isValid(user));
    }

    @Test
    void shouldBeAbleToCheckIfTheUserIsInvalid() {
        Printer printer = new Printer();
        Library library = new Library(printer);
        User user = new User("234-5678","dada");

        assertFalse(library.isValid(user));
    }

    @Test
    void shouldBeAbleToReturnTheAllInformationHoldingMovieObjectWhenQueried() {
        Printer printer = new Printer();
        Movie expectedMovie = new Movie("Dabangg","2015", "Rajesh","10" );

        Movie movie = new Movie("Dabangg","2015");
        Library library = new Library(printer);

        Movie movieContainingAllInfo = library.getQueriedMovie(movie);


        assertEquals(expectedMovie, movieContainingAllInfo);
    }

    @Test
    void shouldNotifyCustomerOnSuccessfulCheckoutOfTheBook() {
        Library library = new Library(new Printer());
        String expectedMessage = "Thank You! Enjoy the book\n\n";

        library.checkOut(new Book("A", "Charles", "2015"), ItemType.BOOK, user);

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldNotifyCustomerOnUnSuccessfulCheckoutOfTheBook() {
        Library library = new Library(new Printer());
        String expectedMessage = "Sorry, that book is not available\n\n";

        library.checkOut(new Book("B", "Charles", "2015"), ItemType.BOOK, user);

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldNotifyCustomerOnSuccessfulReturnOfTheBook() {
        Library library = new Library(new Printer());
        String expectedMessage = "Thank You! Enjoy the book\n\nThank you for returning the book\n\n";

        library.checkOut(new Book("A", "Charles", "2015"), ItemType.BOOK, user);
        library.returnBack(new Book("A", "Charles", "2015"), ItemType.BOOK, user);

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldNotifyCustomerOnUnSuccessfulReturnOfTheBook() {
        Library library = new Library(new Printer());
        String expectedMessage = "Thank You! Enjoy the book\n\nInvalid Return Request\n\n";

        library.checkOut(new Book("A", "Charles", "2015"), ItemType.BOOK, user);
        library.returnBack(new Book("A", "Charles", "2017"), ItemType.BOOK, user);

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void shouldBeAbleToTellIfUserIsAccountableToReturnABookOrNot() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        User user1 = new User("123-4567", "dada");
        User user2 = new User("123-4568", "dada");

        Book book = new Book("A", "Charles", "2015");
        Library library = new Library(printer);

        library.checkOut(book, ItemType.BOOK, user1);
        library.returnBack(book, ItemType.BOOK, user2);

        assertEquals("Thank You! Enjoy the book\n\nInvalid Return Request\n\n", outContent.toString());
    }
}