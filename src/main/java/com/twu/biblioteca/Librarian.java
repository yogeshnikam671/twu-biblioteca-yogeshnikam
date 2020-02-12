package com.twu.biblioteca;

import com.twu.items.Book;
import com.twu.items.Item;
import com.twu.items.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Librarian {
    private HashMap<ItemType, List<Item>> checkedOutItems;
    private List<User> accountableUsers;
    private Printer printer;

    private final static String SUCCESS_CHECKOUT_MESSAGE = "Thank You! Enjoy the book\n";
    private final static String UNSUCCESS_CHECKOUT_MESSAGE = "Sorry, that book is not available\n";
    private final static String SUCCESS_RETURN_MESSAGE = "Thank you for returning the book\n";
    private final static String UNSUCCESS_RETURN_MESSAGE = "This is not a valid book to return\n";

    public Librarian(Printer printer) {
        accountableUsers = new ArrayList<>();
        checkedOutItems = new HashMap<>();
        initializeHashMap();
        this.printer = printer;
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
            printer.print("Invalid Return Request\n");
            return false;
        }
        return true;
    }

    public List<Book> getBooksCheckedOutBy(User user){
        List<Book> checkedOutBooks = new ArrayList<>();
        List<Item> books = checkedOutItems.get(ItemType.BOOK);

        for(int index = 0; index < accountableUsers.size(); index++){
            if(accountableUsers.get(index).equals(user))
                checkedOutBooks.add((Book)books.get(index));
        }
        return checkedOutBooks;
    }

    public void notifyAsUnsuccessfulCheckOut() {
        printer.print(UNSUCCESS_CHECKOUT_MESSAGE);
    }

    public void notifyAsUnsuccessfulReturn() {
        printer.print(UNSUCCESS_RETURN_MESSAGE);
    }

    public void notifyAsSuccessfulCheckout() {
        printer.print(SUCCESS_CHECKOUT_MESSAGE);
    }

    public void notifyAsSuccessfulReturn() {
        printer.print(SUCCESS_RETURN_MESSAGE);
    }

    private void initializeHashMap(){
        checkedOutItems.put(ItemType.BOOK, new ArrayList<>());
        checkedOutItems.put(ItemType.MOVIE, new ArrayList<>());
    }

}
