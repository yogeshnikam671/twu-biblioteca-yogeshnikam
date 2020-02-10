package com.twu.biblioteca;

import static com.twu.biblioteca.Printer.*;

public class Greeter {
    private static String greetingMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public String getGreetingMessage() {
        return greetingMessage;
    }

    public static void greet() {
        print(greetingMessage);
    }
}
