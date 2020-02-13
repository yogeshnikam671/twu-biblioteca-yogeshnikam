package com.twu.biblioteca;

import com.twu.items.Book;
import com.twu.items.Item;
import com.twu.items.ItemType;
import com.twu.items.Movie;
import com.twu.view.Printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.twu.data.DataInitializer.*;

public class Library {
    private HashMap<ItemType, List<Item>> checkedOutItems;
    private List<User> accountableUsers;
    private List<User> users;
    private HashMap<ItemType, List<Item>> items;
    private Printer printer;

    private final static String SUCCESS_MOVIE_CHECKOUT_MESSAGE = "Thank You! Enjoy the movie\n";
    private final static String SUCCESS_BOOK_CHECKOUT_MESSAGE = "Thank You! Enjoy the book\n";
    private final static String UNSUCCESS_CHECKOUT_MESSAGE = "Sorry, that book is not available\n";
    private final static String SUCCESS_RETURN_MESSAGE = "Thank you for returning the book\n";
    private final static String UNSUCCESS_RETURN_MESSAGE = "This is not a valid book to return\n";

    public Library(Printer printer) {
        this.printer = printer;
        initializeHashMap();
    }

    public List<Item> get(ItemType itemType) {
        return items.get(itemType);
    }

    public void show(ItemType itemType) {
        int counter = 1;
        for (Item item : items.get(itemType)) {
            printer.print(counter + ". " + item.getInfo());
            counter++;
        }
    }

    public void checkOut(Item item, ItemType itemType, User... user) {
        List<Item> list = items.get(itemType);
        if (list.contains(item)) {
            list.remove(item);
            markAsCheckedOut(item, itemType, user);
            notifyAsSuccessfulCheckoutOf(itemType);
            return;
        }

        printer.print(UNSUCCESS_CHECKOUT_MESSAGE);
    }

    public void returnBack(Item item, ItemType itemType, User... user) {
        if (itemType == ItemType.BOOK && !isAccountable(user[0], item))
            return;

        List<Item> list = items.get(itemType);
        if (isCheckedOut(item, itemType)) {
            list.add(item);
            markAsReturned(item, itemType, user);
            printer.print(SUCCESS_RETURN_MESSAGE);
            return;
        }

        printer.print(UNSUCCESS_RETURN_MESSAGE);
    }

    public List<Book> getBooksCheckedOutBy(User user) {
        List<Book> checkedOutBooks = new ArrayList<>();
        List<Item> books = checkedOutItems.get(ItemType.BOOK);

        for (int index = 0; index < accountableUsers.size(); index++) {
            if (accountableUsers.get(index).equals(user))
                checkedOutBooks.add((Book) books.get(index));
        }
        return checkedOutBooks;
    }

    public boolean isValid(User user) {
        return users.contains(user);
    }

    public User getQueriedUser(User user) {
        return users.get(users.indexOf(user));
    }

    public Movie getQueriedMovie(Movie movie) {
        List<Item> movies = items.get(ItemType.MOVIE);
        return (Movie) movies.get(movies.indexOf(movie));
    }


    private void initializeHashMap() {
        items = new HashMap<>();
        users = new ArrayList<>();

        List<Item> bookList = new ArrayList<>();
        instantiateWithPreExistingBooks(bookList);
        List<Item> movieList = new ArrayList<>();
        instantiateWithPreExistingMovies(movieList);

        instantiateWithPreExistingUsers(users);

        items.put(ItemType.BOOK, bookList);
        items.put(ItemType.MOVIE, movieList);

        accountableUsers = new ArrayList<>();
        checkedOutItems = new HashMap<>();
        checkedOutItems.put(ItemType.BOOK, new ArrayList<>());
        checkedOutItems.put(ItemType.MOVIE, new ArrayList<>());
    }

    private void markAsCheckedOut(Item item, ItemType itemType, User... user) {
        checkedOutItems.get(itemType).add(item);

        if (user.length != 0)
            accountableUsers.add(user[0]);
    }

    private void markAsReturned(Item item, ItemType itemType, User... user) {
        int index = checkedOutItems.get(itemType).indexOf(item);

        checkedOutItems.get(itemType).remove(item);

        if (user.length != 0 && index != -1)
            accountableUsers.remove(index);
    }

    private boolean isCheckedOut(Item item, ItemType itemType) {
        return checkedOutItems.get(itemType).contains(item);
    }

    private boolean isAccountable(User user, Item book) {
        List<Item> books = checkedOutItems.get(ItemType.BOOK);
        int index = 0;

        for (User u : accountableUsers) {
            if (u.equals(user) && books.get(index).equals(book))
                return true;
            index++;
        }
        printer.print("Invalid Return Request\n");
        return false;
    }

    private void notifyAsSuccessfulCheckoutOf(ItemType itemType){
        if(itemType == ItemType.BOOK)
            printer.print(SUCCESS_BOOK_CHECKOUT_MESSAGE);
        else
            printer.print(SUCCESS_MOVIE_CHECKOUT_MESSAGE);
    }

}

