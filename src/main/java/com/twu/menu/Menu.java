package com.twu.menu;

import java.util.ArrayList;
import java.util.List;

import static com.twu.biblioteca.Printer.print;
import static java.util.Arrays.asList;

public class Menu {
    private List<Integer> optionId; // TODO - do these change? why not final?
    private List<String> options;

    public Menu() { // TODO - why not just use asList? - Now it'll work. And you won't know why. - Go ask people when this does not work. And tell me.
        optionId = new ArrayList<>(asList(1, 2, 3, 4, 5, 6)); // TODO - coupling, introduce a new option, remember to add it here too.
        options = new ArrayList<>(asList("List of books", "Checkout a book", "Return a book","List of movies", "Checkout a movie","Quit the application"));
    }

    public void display() {
        print("\nMenu:\n"); // TODO - fix this global problem
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

        return optionId.contains(input); // TODO - how will you do it when you delete optionId?
    }
}
