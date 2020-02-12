package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

import static com.twu.biblioteca.Printer.print;
import static java.util.Arrays.asList;

public class Menu {
    private List<Integer> optionId;
    private List<String> options;

    public Menu() {
        optionId = new ArrayList<>(asList(1, 2, 3, 4, 5, 6));
        options = new ArrayList<>(asList("List of books", "Checkout a book", "Return a book","List of movies", "Checkout a movie","Quit the application"));
    }

    public void display() {
        print("\nMenu:\n");
        for (int index = 0; index < options.size(); index++) {
            print(optionId.get(index) + " " + options.get(index));
        }
    }

    public boolean isValidOption(String option) {
        int input;

        try {
            input = Integer.parseInt(option);
        } catch (NumberFormatException e) {
            return false;
        }

        return optionId.contains(input);
    }
}
