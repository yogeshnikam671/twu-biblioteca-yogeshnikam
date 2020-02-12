package com.twu.biblioteca;

import java.util.HashMap;
import java.util.Scanner;

import static com.twu.biblioteca.Book.*;
import static com.twu.biblioteca.Printer.*;
import static com.twu.biblioteca.User.*;

public abstract class Option {

    private static HashMap<Integer, Option> OptionsMap;

    public static final Option SHOW_BOOKS = new Option() {
        @Override
        public void process(Library library, Scanner scanner) {
            library.show(ItemType.BOOK);
        }
    };

    public static final Option CHECKOUT_BOOK = new Option() {
        @Override
        public void process(Library library, Scanner scanner) {
            print("Enter Library Number and Password, respectively");
            String libraryNumber = scanner.next();
            String password = scanner.next();

            if(!library.isValid(new User(libraryNumber, password))){
                print("Invalid User !");
                return;

            }

            User user = library.getQueriedUser(new User(libraryNumber, password));
            String input = "1";
            while(input.equals("1")) {
                print("1. See Profile Information\n2. Continue With Checkout Process\n3. Go Back");
                input = scanner.next();
                if(input.equals("1"))
                    user.displayInfo();
            }

            if(input.equals("3")) return;

            print("Enter Title, Author and Year Of Publication, respectively");
            String title = scanner.next();
            String author = scanner.next();
            String year = scanner.next();
            Book book = getQueriedBook(title, author, year);
            library.checkOut(book, ItemType.BOOK, user);
        }
    };

    public static final Option RETURN_BOOK = new Option() {
        @Override
        public void process(Library library, Scanner scanner) {
            print("Enter Library Number and Password, respectively");
            String libraryNumber = scanner.next();
            String password = scanner.next();
            User user = new User(libraryNumber, password);

            if(!library.isValid(user)){
                print("Invalid User !");
                return;

            }

            print("Enter Title, Author and Year Of Publication, respectively");
            String title = scanner.next();
            String author = scanner.next();
            String year = scanner.next();
            Book book = getQueriedBook(title, author, year);
            library.returnBack(book, ItemType.BOOK, user);
        }
    };

    public static final Option QUIT = new Option() {
        @Override
        public void process(Library library, Scanner scanner) {
            System.exit(0);
        }
    };

    static {
        OptionsMap = new HashMap<>();
        initializeHashMap();
    }

    public static Option getOption(int selectedOption) {
        return OptionsMap.get(selectedOption);
    }

    public abstract void process(Library library, Scanner scanner);

    private static void initializeHashMap() {
        OptionsMap.put(1, SHOW_BOOKS);
        OptionsMap.put(2, CHECKOUT_BOOK);
        OptionsMap.put(3, RETURN_BOOK);
        OptionsMap.put(4, QUIT);
    }

}
