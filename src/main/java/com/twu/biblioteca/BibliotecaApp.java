package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Greeter greeter = new Greeter();

        greeter.greet();
        System.out.println();

        Menu menu = new Menu();

        while (true) {
            menu.display();

            Scanner input = new Scanner(System.in);

            String option = input.next();
            while (!menu.isValidOption(option)) {
                System.out.println("Please select a valid option");
                option = input.next();
            }

            process(option, library);
        }
    }

    private static void process(String option, Library library) {
        int selectedOption = Integer.parseInt(option);
        Option choice = Option.getOption(selectedOption);

        choice.process(library);

        System.out.print("\n\n");
    }
}
