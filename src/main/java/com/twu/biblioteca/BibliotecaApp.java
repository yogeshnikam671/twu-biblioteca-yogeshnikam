package com.twu.biblioteca;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Library library = new Library();
        Greeter greeter = new Greeter();
        greeter.greet();
        System.out.println();
        Menu menu = new Menu();
        menu.display();

        Scanner input = new Scanner(System.in);

        String option = input.next();

        while(!menu.isValidOption(option)){
            System.out.println("Please select a valid option");
            option = input.next();
        }

        if(option.equals("1"))
            library.showBooks();
        else
            System.exit(0);
    }
}
