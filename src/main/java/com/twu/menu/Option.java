package com.twu.menu;

import com.twu.biblioteca.Library;
import com.twu.biblioteca.Printer;
import com.twu.biblioteca.User;
import com.twu.items.Book;
import com.twu.items.ItemType;
import com.twu.items.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static com.twu.items.Book.*;
import static com.twu.biblioteca.Printer.*;

public abstract class Option {

    private static HashMap<Integer, Option> OptionsMap;

    public static final Option SHOW_BOOKS = new Option() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer) {
            library.show(ItemType.BOOK);
        }
    };

    public static final Option CHECKOUT_BOOK = new Option() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer) {
            printer.print("Enter Library Number and Password, respectively");
            String libraryNumber = scanner.next();
            String password = scanner.next();

            if(!library.isValid(new User(libraryNumber, password))){
                printer.print("Invalid User !");
                return;

            }

            User user = library.getQueriedUser(new User(libraryNumber, password));
            String input = "1";
            while(input.equals("1")) {
                printer.print("1. See Profile Information\n2. Continue With Checkout Process\n3. Go Back");
                input = scanner.next();
                if(input.equals("1"))
                    user.displayInfo(printer);
            }

            if(input.equals("3")) return;

            printer.print("Enter Title, Author and Year Of Publication, respectively");
            String title = scanner.next();
            String author = scanner.next();
            String year = scanner.next();
            Book book = getQueriedBook(title, author, year);
            library.checkOut(book, ItemType.BOOK, user);
        }
    };

    public static final Option RETURN_BOOK = new Option() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer) {
            printer.print("Enter Library Number and Password, respectively");
            String libraryNumber = scanner.next();
            String password = scanner.next();

            if(!library.isValid(new User(libraryNumber, password))){
                printer.print("Invalid User !");
                return;

            }

            User user = library.getQueriedUser(new User(libraryNumber, password));
            String input = "1";
            while(input.equals("1")) {
                printer.print("1. See Profile Information\n2. Continue With Return Process\n3. Go Back");
                input = scanner.next();
                if(input.equals("1"))
                    user.displayInfo(printer);
            }

            if(input.equals("3")) return;
            printer.print("Enter Title, Author and Year Of Publication, respectively");
            String title = scanner.next();
            String author = scanner.next();
            String year = scanner.next();
            Book book = getQueriedBook(title, author, year);
            library.returnBack(book, ItemType.BOOK, user);
        }
    };

    public static final Option VIEW_CHECKED_OUT_BOOKS = new Option() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer) {
            printer.print("Enter Library Number and Password, respectively");
            String libraryNumber = scanner.next();
            String password = scanner.next();

            if(!library.isValid(new User(libraryNumber, password))){
                printer.print("Invalid User !");
                return;

            }

            User user = library.getQueriedUser(new User(libraryNumber, password));
            String input = "1";
            while(input.equals("1")) {
                printer.print("1. See Profile Information\n2. Continue With Viewing Checked Out Books\n3. Go Back");
                input = scanner.next();
                if(input.equals("1"))
                    user.displayInfo(printer);
            }

            if(input.equals("3")) return;


            List<Book> checkedOutBooks =  library.getBooksCheckedOutBy(user);

            if(checkedOutBooks.size() == 0){
                printer.print("No books are checked out by you\n");
                return;
            }

            printer.print("Books Checked Out :\n");
            for(Book book : checkedOutBooks){
                printer.print(book.getInfo());
            }
        }
    };

    public static final Option QUIT = new Option() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer) {
            System.exit(0);
        }
    };

    public static final Option CHECKOUT_MOVIE = new Option() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer) {
            printer.print("Enter Name and Year Of Release, respectively");
            String name = scanner.next();
            String year = scanner.next();
            Movie movie = library.getQueriedMovie(new Movie(name, year));
            library.checkOut(movie, ItemType.MOVIE);
        }
    };

    public static final Option SHOW_MOVIES = new Option() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer) {
            library.show(ItemType.MOVIE);
        }
    };

    static {
        OptionsMap = new HashMap<>();
        initializeHashMap();
    }

    public static Option getOption(int selectedOption) {
        return OptionsMap.get(selectedOption);
    }

    public abstract void process(Library library, Scanner scanner, Printer printer);

    private static void initializeHashMap() {
        OptionsMap.put(1, SHOW_BOOKS);
        OptionsMap.put(2, CHECKOUT_BOOK);
        OptionsMap.put(3, RETURN_BOOK);
        OptionsMap.put(4, VIEW_CHECKED_OUT_BOOKS);

        OptionsMap.put(5, SHOW_MOVIES);
        OptionsMap.put(6, CHECKOUT_MOVIE);
        OptionsMap.put(7, QUIT);
    }

}
