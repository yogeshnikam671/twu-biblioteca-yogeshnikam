package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.*;

public class Menu {
    private List<Integer> optionId;
    private List<String> options;

    public Menu() {
        optionId = new ArrayList<>(asList(1, 2, 3, 4));
        options = new ArrayList<>(asList("List of books", "Checkout a book", "Return a book","Quit the application"));
    }

    public void display() {

        for (int i = 0; i < 4; i++) {
            System.out.println(optionId.get(i) + " " + options.get(i));
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
