package com.twu.menu;

import com.twu.biblioteca.Library;
import com.twu.view.Printer;
import com.twu.biblioteca.User;
import com.twu.items.Book;
import com.twu.items.ItemType;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static com.twu.items.Book.*;

public abstract class BookMenuOption {

    private static HashMap<Integer, BookMenuOption> optionsMap;

    private static final BookMenuOption VIEW_INFO = new BookMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer, User user) {
            User loggedInUser = library.getQueriedUser(user);
            loggedInUser.displayInfo(printer);
        }
    };

    private static final BookMenuOption CHECKOUT_BOOK = new BookMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer, User user) {
            printer.print("Enter Title, Author and Year Of Publication, respectively");
            String title = scanner.next();
            String author = scanner.next();
            String year = scanner.next();
            Book book = getQueriedBook(title, author, year);
            library.checkOut(book, ItemType.BOOK, user);
        }
    };

    private static final BookMenuOption RETURN_BOOK = new BookMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer, User user) {
            printer.print("Enter Title, Author and Year Of Publication, respectively");
            String title = scanner.next();
            String author = scanner.next();
            String year = scanner.next();
            Book book = getQueriedBook(title, author, year);
            library.returnBack(book, ItemType.BOOK, user);
        }
    };

    private static final BookMenuOption VIEW_CHECKED_OUT_BOOKS = new BookMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer, User user) {

            List<Book> checkedOutBooks = library.getBooksCheckedOutBy(user);

            if (checkedOutBooks.size() == 0) {
                printer.print("No books are checked out by you\n");
                return;
            }

            printer.print("\nBooks Checked Out :\n");
            int counter = 1;
            for (Book book : checkedOutBooks) {
                printer.print(counter + ". " + book.getInfo());
                counter++;
            }
        }
    };

    private static final BookMenuOption GO_BACK = new BookMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer, User user) {
            MainMenuOption.flag = false;
        }
    };


    static {
        optionsMap = new HashMap<>();
        initializeHashMap();
    }

    public static BookMenuOption getOption(int selectedOption) {
        return optionsMap.get(selectedOption);
    }

    public abstract void process(Library library, Scanner scanner, Printer printer, User user);

    private static void initializeHashMap() {
        optionsMap.put(1, VIEW_INFO);
        optionsMap.put(2, CHECKOUT_BOOK);
        optionsMap.put(3, RETURN_BOOK);
        optionsMap.put(4, VIEW_CHECKED_OUT_BOOKS);
        optionsMap.put(5, GO_BACK);
    }

}
