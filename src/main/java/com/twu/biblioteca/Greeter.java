package com.twu.biblioteca;

public class Greeter {
    private String greetingMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public Greeter() {// TODO: don't need
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }

    public void greet() {
        System.out.println(greetingMessage);
    } // TODO : need test, is this greeters responsibility ?
}
