package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    User user = new User("123-4567", "dada");

    @Test
    void shouldBeAbleToShowListOfBooks() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        List<Book> books = new ArrayList<>();
        books.add(new Book("A", "Charles", "2015"));
        books.add(new Book("B", "Henry", "2017"));
        books.add(new Book("C", "Richard", "2012"));

        Library library = new Library(new Librarian());


        library.show(ItemType.BOOK);

        assertEquals(books, library.get(ItemType.BOOK));
        assertEquals("1. A\tCharles\t2015\n2. B\tHenry\t2017\n3. C\tRichard\t2012\n", outContent.toString());
    }

    @Test
    void shouldAllowTheCustomerToCheckOutABook() {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);

        List<Book> books = new ArrayList<>();
        books.add(new Book("A", "Charles", "2015"));
        books.add(new Book("B", "Henry", "2017"));

        Book book = new Book("C", "Richard", "2012");

        library.checkOut(book, ItemType.BOOK, user);

        assertEquals(books, library.get(ItemType.BOOK));
    }

    @Test
    void shouldAllowTheCustomerToReturnABook() {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Book book = new Book("C", "Richard", "2012");

        library.checkOut(book, ItemType.BOOK, user);
        library.returnBack(book, ItemType.BOOK, user);

        assertTrue(library.get(ItemType.BOOK).contains(book));
    }

    @Test
    void shouldBeAbleToShowListOfMovies() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Library library = new Library(new Librarian());

        library.show(ItemType.MOVIE);

       assertEquals("1. Dabangg\t2015\tRajesh\t10\n2. Bharat\t2019\tSuresh\t1\n", outContent.toString());
    }

    @Test
    void shouldAllowTheCustomerToCheckOutAMovie() {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Dabangg","2015", "Rajesh","10" ));

        Movie movie = new Movie("Bharat","2019","Suresh","1");

        library.checkOut(movie, ItemType.MOVIE);

        assertEquals(movies, library.get(ItemType.MOVIE));
    }

    @Test
    void shouldBeAbleToCheckIfTheUserIsValid() {
        Library library = new Library(new Librarian());
        User user = new User("123-4567","dada");

        assertTrue(library.isValid(user));
    }

    @Test
    void shouldBeAbleToCheckIfTheUserIsInvalid() {
        Library library = new Library(new Librarian());
        User user = new User("234-5678","dada");

        assertFalse(library.isValid(user));
    }

    @Test
    void shouldBeAbleToReturnTheAllInformationHoldingMovieObjectWhenQueried() {
        Movie expectedMovie = new Movie("Dabangg","2015", "Rajesh","10" );

        Movie movie = new Movie("Dabangg","2015");
        Library library = new Library(new Librarian());

        Movie movieContainingAllInfo = library.getQueriedMovie(movie);


        assertEquals(expectedMovie, movieContainingAllInfo);
    }
}