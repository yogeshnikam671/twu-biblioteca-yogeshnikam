package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

import static com.twu.biblioteca.Printer.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private Librarian librarian;

    public Library() {
        instantiateWithPreExistingBooks();
    }

    public Library(Librarian librarian) {
        books.add(new Book("A", "Charles", 2015));   // pre-existing books
        books.add(new Book("B", "Henry", 2017));
        books.add(new Book("C", "Richard", 2012));

        this.librarian = librarian;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void showBooks() {
        int counter = 1;
        for (Book book : books) {
            print(counter + ". " + book.getInfo());
            counter++;
        }
    }

    public void checkOut(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            librarian.markAsCheckedOut(book);
            librarian.notifyAsSuccessfulCheckout();
            return;
        }

        librarian.notifyAsUnsuccessfulCheckOut();
    }

    public void returnBack(Book book) {
        if (librarian.isCheckedOut(book)) {
            books.add(book);
            librarian.markAsReturned(book);
            librarian.notifyAsSuccessfulReturn();
            return;
        }

        librarian.notifyAsUnsuccessfulReturn();
    }

    private void instantiateWithPreExistingBooks(){
        books.add(new Book("A", "Charles", 2015));
        books.add(new Book("B", "Henry", 2017));
        books.add(new Book("C", "Richard", 2012));
    }
}

