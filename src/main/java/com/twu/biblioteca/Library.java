package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.twu.biblioteca.Printer.*;

public class Library {

    private List<User> users;
    private HashMap<ItemType, List<Item>> items;
    private Librarian librarian;

    public Library(Librarian librarian) {
        items = new HashMap<>();
        initializeHashMap();
        instantiateWithPreExistingUsers();
        this.librarian = librarian;
    }


    public List<Item> get(ItemType itemType) {
        return items.get(itemType);
    }

    public void show(ItemType itemType) {
        int counter = 1;
        for (Item item : items.get(itemType)) {
            print(counter + ". " + item.getInfo());
            counter++;
        }
    }

    public void checkOut(Item item, ItemType itemType) {
        List<Item> list = items.get(itemType);
        if (list.contains(item)) {
            list.remove(item);
            librarian.markAsCheckedOut(item, itemType);
            librarian.notifyAsSuccessfulCheckout();
            return;
        }

        librarian.notifyAsUnsuccessfulCheckOut();
    }

    public void returnBack(Item item, ItemType itemType) {
        List<Item> list = items.get(itemType);
        if (librarian.isCheckedOut(item, itemType)) {
            list.add(item);
            librarian.markAsReturned(item, itemType);
            librarian.notifyAsSuccessfulReturn();
            return;
        }

        librarian.notifyAsUnsuccessfulReturn();
    }

    public boolean isValid(User user) {
        if(users.contains(user))
            return true;
        return false;
    }

    private void instantiateWithPreExistingBooks(List<Item> items){
        items.add(new Book("A", "Charles", "2015"));
        items.add(new Book("B", "Henry", "2017"));
        items.add(new Book("C", "Richard", "2012"));
    }

    private void instantiateWithPreExistingMovies(List<Item> items){
        items.add(new Movie("Dabangg","2015", "Rajesh","10" ));
        items.add(new Movie("Bharat","2019","Suresh","1"));
    }

    private void initializeHashMap(){
        List<Item> bookList = new ArrayList<>();
        instantiateWithPreExistingBooks(bookList);

        List<Item> movieList = new ArrayList<>();
        instantiateWithPreExistingMovies(movieList);

        items.put(ItemType.BOOK, bookList);
        items.put(ItemType.MOVIE, movieList);
    }

    private void instantiateWithPreExistingUsers() {
        users = new ArrayList<>();
        users.add(new User("123-4567", "dada"));
        users.add(new User("123-4568","dada1"));
        users.add(new User("123-4569","dada2"));
    }

}

