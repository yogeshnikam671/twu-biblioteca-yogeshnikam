package com.twu.menu;

import com.twu.biblioteca.Printer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    @Test
    void shouldBeAbleToDisplayOptions() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Menu menu = new Menu();

        menu.display(new Printer());

        assertEquals("\nMenu:\n\n1 List of books\n2 Checkout a book\n3 Return a book\n4 List of movies\n5 Checkout a movie\n6 Quit the application\n", outContent.toString());
    }

    @Test
    void shouldBeAbleCheckInputFromUserIsValid() {
        Menu menu = new Menu();

        boolean isValid = menu.isValidOption("1");

        assertTrue(isValid);
    }

    @Test
    void shouldBeAbleCheckInputFromUserIsInValid() {
        Menu menu = new Menu();

        boolean isValid = menu.isValidOption("10");

        assertFalse(isValid);
    }

    @Test
    void shouldBeAbleCheckInputFromUserIsOfInValidType() {
        Menu menu = new Menu();

        boolean isValid = menu.isValidOption("abcd");

        assertFalse(isValid);
    }
}