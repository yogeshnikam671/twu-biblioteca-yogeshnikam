package com.twu.biblioteca;

import com.twu.biblioteca.Menu;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuTest {
    @Test
    void shouldBeAbleToDisplayOptions() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Menu menu = new Menu();

        menu.display();

        assertEquals("1 List of books\n2 Quit the application\n", outContent.toString());
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

        boolean isValid = menu.isValidOption("3");

        assertFalse(isValid);
    }

    @Test
    void shouldBeAbleCheckInputFromUserIsOfInValidType() {
        Menu menu = new Menu();

        boolean isValid = menu.isValidOption("abcd");

        assertFalse(isValid);
    }
}