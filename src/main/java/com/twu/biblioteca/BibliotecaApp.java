package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        greeter.greet();
        System.out.println();
        Menu menu = new Menu();
        menu.display();
    }
}
