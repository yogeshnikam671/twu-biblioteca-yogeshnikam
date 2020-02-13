package com.twu.biblioteca;

import com.twu.view.Greeter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreeterTest {
    @Test
    void shouldBeAbleToGreetWithAMessage() {
        String expectedMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        Greeter greeter = new Greeter();
        assertEquals(expectedMessage, greeter.getGreetingMessage());
    }
}