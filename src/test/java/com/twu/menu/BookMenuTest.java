package com.twu.menu;

import com.twu.view.Printer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BookMenuTest {
    @Test
    void shouldBeAbleToDisplayOptions() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        BookMenu bookMenu = new BookMenu();

        bookMenu.display(new Printer());

        assertEquals("\nMenu:\n\n1 View profile information\n2 Checkout a book\n3 Return a book\n4 View checked-out books\n5 Back to Main Menu\n", outContent.toString());
    }

    @Test
    void shouldBeAbleCheckInputFromUserIsValid() {
        BookMenu bookMenu = new BookMenu();

        boolean isValid = bookMenu.isValidOption("1");

        assertTrue(isValid);
    }

    @Test
    void shouldBeAbleCheckInputFromUserIsInValid() {
        BookMenu bookMenu = new BookMenu();

        boolean isValid = bookMenu.isValidOption("10");

        assertFalse(isValid);
    }

    @Test
    void shouldBeAbleCheckInputFromUserIsOfInValidType() {
        BookMenu bookMenu = new BookMenu();

        boolean isValid = bookMenu.isValidOption("abcd");

        assertFalse(isValid);
    }
}
