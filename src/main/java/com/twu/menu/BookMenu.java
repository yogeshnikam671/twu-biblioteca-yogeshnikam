package com.twu.menu;

import com.twu.biblioteca.Printer;

import java.util.ArrayList;
import java.util.List;

public class BookMenu {
    private final List<String> options;

    public BookMenu() {
        options = new ArrayList<>();
        options.add("View profile information");
        options.add("Checkout a book");
        options.add("Return a book");
        options.add("View checked-out books");

        options.add("Back to Main Menu");
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
