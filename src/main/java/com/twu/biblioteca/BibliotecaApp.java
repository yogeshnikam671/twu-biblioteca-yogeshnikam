package com.twu.biblioteca;

import com.twu.menu.MainMenu;
import com.twu.menu.MainMenuOption;

import java.util.Scanner;

import static com.twu.biblioteca.Greeter.greet;
import static com.twu.menu.MainMenuOption.getOption;
import static java.lang.Integer.parseInt;

public class BibliotecaApp {
    private  Scanner scanner;
    private  Library library;
    private MainMenu mainMenu;
    private  Printer printer;

    public BibliotecaApp(Scanner scanner, Library library, MainMenu mainMenu, Printer printer) {
        this.scanner = scanner;
        this.library = library;
        this.mainMenu = mainMenu;
        this.printer = printer;
    }

    public static void main(String[] args) {

        Printer printer = new Printer();
        Scanner scanner = new Scanner(System.in);
        Librarian librarian = new Librarian(printer);
        Library library = new Library(librarian, printer, new DataInitializer());
        MainMenu mainMenu = new MainMenu();

        BibliotecaApp app = new BibliotecaApp(scanner, library, mainMenu, printer);

        app.start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        greet(printer);
        printer.print();

        while (true) {
            mainMenu.display(printer);

            String choice = scanner.next();
            if (!mainMenu.isValidOption(choice)) {
                printer.print("Please select a valid option\n");
                continue;
            }

            process(parseInt(choice));
        }
    }

    private void process(int choice) {
        MainMenuOption mainMenuOption = getOption(choice);
        mainMenuOption.process(library, scanner, printer);
    }
}
