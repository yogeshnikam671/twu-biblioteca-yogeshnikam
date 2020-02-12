package com.twu.menu;

import com.twu.biblioteca.Printer;
import java.util.List;

import static java.util.Arrays.asList;

public class Menu {
    private final List<String> options;

    public Menu() {
        options = asList("List of books", "Checkout a book", "Return a book","List of movies", "Checkout a movie","Quit the application");
    }

    public void display(Printer printer) {
        printer.print("\nMenu:\n");
        for (int index = 1; index <= options.size(); index++) {
            printer.print(index + " " + options.get(index - 1));
        }
    }

    public boolean isValidOption(String option) {
        int input;

        try {
            input = Integer.parseInt(option);
        } catch (NumberFormatException e) {
            return false;
        }

        return (input > 0 && input <= options.size());
    }
}
