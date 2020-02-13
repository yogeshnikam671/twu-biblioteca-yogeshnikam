package com.twu.view;


public class Greeter {
    private static String greetingMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public String getGreetingMessage() {
        return greetingMessage;
    }

    public static void greet(Printer printer) {
        printer.print(greetingMessage);
    }
}
