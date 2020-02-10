package com.twu.biblioteca;

import java.util.Scanner;

import static com.twu.biblioteca.Option.getOption;
import static java.lang.Integer.parseInt;

public class BibliotecaApp {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);

        new Greeter().greet();

        Printer.print();

        Menu menu = new Menu();

        while (true) {
            menu.display();

            Scanner scanner = new Scanner(System.in);

            String choice = scanner.next();
            if (!menu.isValidOption(choice)) {
                Printer.print("Please select a valid option");
                continue;
            }

            process(library, parseInt(choice));
        }
    }

    private static void process(Library library, int choice) {
        Option option = getOption(choice);
        option.process(library);
        Printer.print("\n\n");
    }
}
