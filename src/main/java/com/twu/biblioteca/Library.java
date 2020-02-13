package com.twu.biblioteca;

import com.twu.items.Book;
import com.twu.items.Item;
import com.twu.items.ItemType;
import com.twu.items.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Library {

    private List<User> users;
    private HashMap<ItemType, List<Item>> items;
    private Librarian librarian;
    private Printer printer;
    private DataInitializer dataInitializer;

    public Library(Librarian librarian, Printer printer, DataInitializer dataInitializer) {
        this.librarian = librarian;
        this.printer = printer;
        this.dataInitializer = dataInitializer; 
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
            librarian.markAsCheckedOut(item, itemType, user);
            librarian.notifyAsSuccessfulCheckout();
            return;
        }

        librarian.notifyAsUnsuccessfulCheckOut();
    }

    public void returnBack(Item item, ItemType itemType, User... user) {
        if (itemType == ItemType.BOOK && !librarian.isAccountable(user[0], item))
            return;

        List<Item> list = items.get(itemType);
        if (librarian.isCheckedOut(item, itemType, user)) {
            list.add(item);
            librarian.markAsReturned(item, itemType, user);
            librarian.notifyAsSuccessfulReturn();
            return;
        }

        librarian.notifyAsUnsuccessfulReturn();
    }

    public List<Book> getBooksCheckedOutBy(User user) {
        return librarian.getBooksCheckedOutBy(user);
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
        dataInitializer.instantiateWithPreExistingBooks(bookList);

        List<Item> movieList = new ArrayList<>();
        dataInitializer.instantiateWithPreExistingMovies(movieList);

        dataInitializer.instantiateWithPreExistingUsers(users);

        items.put(ItemType.BOOK, bookList);
        items.put(ItemType.MOVIE, movieList);
    }

}

