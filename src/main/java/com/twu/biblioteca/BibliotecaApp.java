package com.twu.biblioteca;

import com.twu.menu.Menu;
import com.twu.menu.Option;

import java.util.Scanner;

import static com.twu.biblioteca.Greeter.*;
import static com.twu.menu.Option.getOption;
import static com.twu.biblioteca.Printer.*;
import static java.lang.Integer.parseInt;

public class BibliotecaApp {
    private static Scanner scanner;
    private static Library library;
    private Menu menu;

    public BibliotecaApp(Scanner scanner, Library library, Menu menu) {
        BibliotecaApp.scanner = scanner;
        BibliotecaApp.library = library;
        this.menu = menu;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Menu menu = new Menu();

        BibliotecaApp app = new BibliotecaApp(scanner, library, menu);

        app.start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
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

    private void process(int choice) {
        Option option = getOption(choice);
        option.process(library, scanner);
    }
}
