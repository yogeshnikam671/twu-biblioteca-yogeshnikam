package com.twu.biblioteca;

import java.util.Scanner;

import static com.twu.biblioteca.Option.getOption;
import static com.twu.biblioteca.Printer.*;
import static java.lang.Integer.parseInt;

public class BibliotecaApp {
    private static Scanner scanner = new Scanner(System.in);


    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);

        new Greeter().greet();

        print();

        Menu menu = new Menu();

        while (true) {
            menu.display();

            String choice = scanner.next();
            if (!menu.isValidOption(choice)) {
                print("Please select a valid option");
                continue;
            }

            process(library, parseInt(choice));
        }
    }

    private static void process(Library library, int choice) {
        Option option = getOption(choice);
        option.process(library, scanner);
    }
}
