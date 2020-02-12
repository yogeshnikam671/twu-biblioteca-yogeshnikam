package com.twu.menu;

import com.twu.biblioteca.Printer;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<String> options;

    public Menu() {
        options = new ArrayList<>();
        options.add("List of books");
        options.add("Checkout a book");
        options.add("Return a book");
        options.add("View checked-out books");

        options.add("List of movies");
        options.add("Checkout a movie");
        options.add("Quit the application");
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
