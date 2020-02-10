package com.twu.biblioteca;

import java.util.Scanner;

import static com.twu.biblioteca.Greeter.*;
import static com.twu.biblioteca.Option.getOption;
import static com.twu.biblioteca.Printer.*;
import static java.lang.Integer.parseInt;

public class BibliotecaApp {
    private static Scanner scanner;
    private static Librarian librarian;
    private static Library library;
    private Menu menu;

    public BibliotecaApp(Scanner scanner, Library library, Librarian librarian, Menu menu) {
        BibliotecaApp.scanner = scanner;
        BibliotecaApp.library = library;
        BibliotecaApp.librarian = librarian;
        this.menu = menu;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Menu menu = new Menu();

        BibliotecaApp app = new BibliotecaApp(scanner, library, librarian, menu);

        app.start();
    }

    public void start(){
        greet();
        print();

        while (true) {
            menu.display();

            String choice = scanner.next();
            if (!menu.isValidOption(choice)) {
                print("Please select a valid option");
                continue;
            }

            process(parseInt(choice));
        }
    }

    private static void process(int choice) {
        Option option = getOption(choice);
        option.process(library, scanner);
    }
}
