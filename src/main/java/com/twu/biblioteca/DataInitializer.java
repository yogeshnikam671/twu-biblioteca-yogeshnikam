package com.twu.biblioteca;

import com.twu.items.Book;
import com.twu.items.Item;
import com.twu.items.Movie;

import java.util.ArrayList;
import java.util.List;

public class DataInitializer {

    public void instantiateWithPreExistingUsers(List<User> users) {
        users.add(new User("123-4567", "dada", "A", "A@mail.com", "1234567"));
        users.add(new User("123-4568", "dada1", "B", "B@mail.com", "1234568"));
        users.add(new User("123-4569", "dada2", "C", "C@mail.com", "1234569"));
    }
    public void instantiateWithPreExistingMovies(List<Item> items) {
        items.add(new Movie("Dabangg", "2015", "Rajesh", "10"));
        items.add(new Movie("Bharat", "2019", "Suresh", "1"));
    }
    public void instantiateWithPreExistingBooks(List<Item> items) {
        items.add(new Book("A", "Charles", "2015"));
        items.add(new Book("B", "Henry", "2017"));
        items.add(new Book("C", "Richard", "2012"));
    }


}
