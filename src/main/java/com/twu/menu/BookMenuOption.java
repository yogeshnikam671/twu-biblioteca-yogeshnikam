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

    private static HashMap<Integer, BookMenuOption> OptionsMap;

    public static final BookMenuOption VIEW_INFO = new BookMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer, User user) {
            User loggedInUser = library.getQueriedUser(user);
            loggedInUser.displayInfo(printer);
            return;
        }
    };

    public static final BookMenuOption CHECKOUT_BOOK = new BookMenuOption() {
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

    public static final BookMenuOption RETURN_BOOK = new BookMenuOption() {
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

    public static final BookMenuOption VIEW_CHECKED_OUT_BOOKS = new BookMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer, User user) {

            List<Book> checkedOutBooks =  library.getBooksCheckedOutBy(user);

            if(checkedOutBooks.size() == 0){
                printer.print("No books are checked out by you\n");
                return;
            }

            printer.print("\nBooks Checked Out :\n");
            int counter = 1;
            for(Book book : checkedOutBooks){
                printer.print(counter + ". " +book.getInfo());
                counter++;
            }
        }
    };

    public static final BookMenuOption GO_BACK = new BookMenuOption() {
        @Override
        public void process(Library library, Scanner scanner, Printer printer, User user) {
            MainMenuOption.flag = false;
            return;
        }
    };


    static {
        OptionsMap = new HashMap<>();
        initializeHashMap();
    }

    public static BookMenuOption getOption(int selectedOption) {
        return OptionsMap.get(selectedOption);
    }

    public abstract void process(Library library, Scanner scanner, Printer printer, User user);

    private static void initializeHashMap() {
        OptionsMap.put(1, VIEW_INFO);
        OptionsMap.put(2, CHECKOUT_BOOK);
        OptionsMap.put(3, RETURN_BOOK);
        OptionsMap.put(4, VIEW_CHECKED_OUT_BOOKS);
        OptionsMap.put(5, GO_BACK);
    }

}
