package com.twu.menu;

import com.twu.biblioteca.Printer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {
    @Test
    void shouldBeAbleToDisplayOptions() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        MainMenu mainMenu = new MainMenu();

        mainMenu.display(new Printer());

        assertEquals("\nMenu:\n\n1 List of books\n2 Log in\n3 List of movies\n4 Checkout a movie\n5 Quit the application\n", outContent.toString());
    }

    @Test
    void shouldBeAbleCheckInputFromUserIsValid() {
        MainMenu mainMenu = new MainMenu();

        boolean isValid = mainMenu.isValidOption("1");

        assertTrue(isValid);
    }

    @Test
    void shouldBeAbleCheckInputFromUserIsInValid() {
        MainMenu mainMenu = new MainMenu();

        boolean isValid = mainMenu.isValidOption("10");

        assertFalse(isValid);
    }

    @Test
    void shouldBeAbleCheckInputFromUserIsOfInValidType() {
        MainMenu mainMenu = new MainMenu();

        boolean isValid = mainMenu.isValidOption("abcd");

        assertFalse(isValid);
    }
}