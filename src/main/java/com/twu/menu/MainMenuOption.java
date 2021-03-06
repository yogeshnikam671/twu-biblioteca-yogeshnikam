package com.twu.menu;

import com.twu.biblioteca.Library;
import com.twu.view.Printer;
import com.twu.biblioteca.User;
import com.twu.items.ItemType;
import com.twu.items.Movie;

import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public abstract class MainMenuOption {
    public static boolean flag = true;

    private static HashMap<Integer, MainMenuOption> optionsMap;


    private static final MainMenuOption SHOW_BOOKS = new MainMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer) {
            library.show(ItemType.BOOK);
        }
    };

    private static final MainMenuOption LOG_IN = new MainMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer) {

            printer.print("Enter Library Number and Password, respectively");
            String libraryNumber = scanner.next();
            String password = scanner.next();
            User user = new User(libraryNumber, password);

            if (!library.isValid(user)) {
                printer.print("Invalid User !");
                return;

            }
            BookMenu bookMenu = new BookMenu();

            flag = true;
            while (flag) {
                bookMenu.display(printer);

                String choice = scanner.next();
                if (!bookMenu.isValidOption(choice)) {
                    printer.print("Please select a valid option\n");
                    continue;
                }

                BookMenuOption bookMenuOption = BookMenuOption.getOption(parseInt(choice));
                bookMenuOption.process(library, scanner, printer, user);
            }

        }
    };

    private static final MainMenuOption CHECKOUT_MOVIE = new MainMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer) {
            printer.print("Enter Name and Year Of Release, respectively");
            String name = scanner.next();
            String year = scanner.next();
            Movie movie = library.getQueriedMovie(new Movie(name, year));
            library.checkOut(movie, ItemType.MOVIE);
        }
    };

    private static final MainMenuOption SHOW_MOVIES = new MainMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer) {
            library.show(ItemType.MOVIE);
        }
    };

    private static final MainMenuOption QUIT = new MainMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer) {
            System.exit(0);
        }
    };


    static {
        optionsMap = new HashMap<>();
        initializeHashMap();
    }

    public static MainMenuOption getOption(int selectedOption) {
        return optionsMap.get(selectedOption);
    }

    public abstract void process(Library library, Scanner scanner, Printer printer);

    private static void initializeHashMap() {
        optionsMap.put(1, SHOW_BOOKS);
        optionsMap.put(2, LOG_IN);
        optionsMap.put(3, SHOW_MOVIES);
        optionsMap.put(4, CHECKOUT_MOVIE);
        optionsMap.put(5, QUIT);
    }

}
