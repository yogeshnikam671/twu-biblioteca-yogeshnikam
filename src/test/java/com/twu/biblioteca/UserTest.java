package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void shouldBeAbleToGetComparedWithAnotherUser() {
        String libraryNumber = "123-4567";
        String password = "1234";
        User user1 = new User(libraryNumber, password);

        User user2 = new User("345-6789", "pass");

        assertNotEquals(user1, user2);
    }

    @Test
    void shouldBeAbleToDisplayUserInformation() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Printer printer = new Printer();

        Library library = new Library(new Librarian(printer), printer);
        User user = library.getQueriedUser(new User("123-4567", "dada"));

        user.displayInfo(printer);

        assertEquals("Name: " + "A" + "\tEmail: " + "A@mail.com" + "\tPhone: " + "1234567\n", outContent.toString());
    }
}