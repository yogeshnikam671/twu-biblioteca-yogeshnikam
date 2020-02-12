package com.twu.biblioteca;

import com.twu.menu.Menu;
import com.twu.menu.Option;

import java.util.Scanner;

import static com.twu.biblioteca.Greeter.greet;
import static com.twu.menu.Option.getOption;
import static java.lang.Integer.parseInt;

public class BibliotecaApp {
    private  Scanner scanner;
    private  Library library;
    private  Menu menu;
    private  Printer printer;

    public BibliotecaApp(Scanner scanner, Library library, Menu menu, Printer printer) {
        this.scanner = scanner;
        this.library = library;
        this.menu = menu;
        this.printer = printer;
    }

    public static void main(String[] args) {

        Printer printer = new Printer();
        Scanner scanner = new Scanner(System.in);
        Librarian librarian = new Librarian(printer);
        Library library = new Library(librarian, printer);
        Menu menu = new Menu();

        BibliotecaApp app = new BibliotecaApp(scanner, library, menu, printer);

        app.start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        greet(printer);
        printer.print();

        while (true) {
            menu.display(printer);

            String choice = scanner.next();
            if (!menu.isValidOption(choice)) {
                printer.print("Please select a valid option");
                continue;
            }

            process(parseInt(choice));
        }
    }

    private void process(int choice) {
        Option option = getOption(choice);
        option.process(library, scanner, printer);
    }
}
