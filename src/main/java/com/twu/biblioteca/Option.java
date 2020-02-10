package com.twu.biblioteca;

import java.util.HashMap;

import static com.twu.biblioteca.Printer.*;

public abstract class Option {

    private static HashMap<Integer, Option> OptionsMap;

    public static final Option SHOW_BOOKS = new Option() {
        @Override
        public void process(Library library) {
            library.showBooks();
        }
    };

    public static final Option CHECKOUT_BOOK = new Option() {
        @Override
        public void process(Library library) {
            print("Enter Title, Author and Year Of Publication, respectively");
            Book book = Book.getQueriedBook();
            library.checkOut(book);
        }
    };

    public static final Option RETURN_BOOK = new Option() {
        @Override
        public void process(Library library) {
            print("Enter Title, Author and Year Of Publication, respectively");
            Book book = Book.getQueriedBook();
            library.returnBack(book);
        }
    };

    public static final Option QUIT = new Option() {
        @Override
        public void process(Library library) {
            System.exit(0);
        }
    };

    static {
        OptionsMap = new HashMap<>();
        initializeHashMap();
    }

    public abstract void process(Library library);

    private static void initializeHashMap() {
        OptionsMap.put(1, SHOW_BOOKS);
        OptionsMap.put(2, CHECKOUT_BOOK);
        OptionsMap.put(3, RETURN_BOOK);
        OptionsMap.put(4, QUIT);
    }

    public static Option getOption(int selectedOption) {
        return OptionsMap.get(selectedOption);
    }
}
