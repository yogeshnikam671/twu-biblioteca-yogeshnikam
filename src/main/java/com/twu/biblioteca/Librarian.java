package com.twu.biblioteca;

import com.twu.items.Item;
import com.twu.items.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.twu.biblioteca.Printer.*;

public class Librarian {
    private HashMap<ItemType, List<Item>> checkedOutItems;
    private List<User> accountableUsers;

    private final static String SUCCESS_CHECKOUT_MESSAGE = "Thank You! Enjoy the book";
    private final static String UNSUCCESS_CHECKOUT_MESSAGE = "Sorry, that book is not available";
    private final static String SUCCESS_RETURN_MESSAGE = "Thank you for returning the book";
    private final static String UNSUCCESS_RETURN_MESSAGE = "This is not a valid book to return";

    public Librarian() {
        accountableUsers = new ArrayList<>();
        checkedOutItems = new HashMap<>();
        initializeHashMap();
    }

    public List<Item> getCheckedOut(ItemType itemType) {
        return checkedOutItems.get(itemType);
    }

    public void markAsCheckedOut(Item item, ItemType itemType, User... user) {
        checkedOutItems.get(itemType).add(item);

        if(user.length != 0)
            accountableUsers.add(user[0]);
    }

    public void markAsReturned(Item item, ItemType itemType, User... user) {
        checkedOutItems.get(itemType).remove(item);
    }

    public boolean isCheckedOut(Item item, ItemType itemType, User... user) {
        return checkedOutItems.get(itemType).contains(item);
    }

    public boolean isAccountable(User user, Item book){
        List<Item> books = checkedOutItems.get(ItemType.BOOK);
        if(accountableUsers.indexOf(user) != books.indexOf(book))
        {
            print("You are not accountable to return this book\n");
            return false;
        }
        return true;
    }

    public void notifyAsUnsuccessfulCheckOut() {
        print(UNSUCCESS_CHECKOUT_MESSAGE);
    }

    public void notifyAsUnsuccessfulReturn() {
        print(UNSUCCESS_RETURN_MESSAGE);
    }

    public void notifyAsSuccessfulCheckout() {
        print(SUCCESS_CHECKOUT_MESSAGE);
    }

    public void notifyAsSuccessfulReturn() {
        print(SUCCESS_RETURN_MESSAGE);
    }

    private void initializeHashMap(){
        checkedOutItems.put(ItemType.BOOK, new ArrayList<>());
        checkedOutItems.put(ItemType.MOVIE, new ArrayList<>());
    }

}
