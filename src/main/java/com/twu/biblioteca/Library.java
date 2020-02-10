package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Library {
    private List<Book> books = new ArrayList<>();
    private Librarian librarian;

    public Library() {
        books.add(new Book("A", "Charles", 2015));   // pre-existing books, TODO: get rid of comment
        books.add(new Book("B", "Henry", 2017));
        books.add(new Book("C", "Richard", 2012));
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
            System.out.println(counter + ". " + book.getInfo());
            counter++;
        }
    }

    public void checkOut(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            librarian.markAsCheckedOut(book);
            return;
        }

        librarian.notifyAsUnsuccessfulCheckOut();
    }

    public void returnBack(Book book) {
        if (librarian.isValid(book)) {
            books.add(book);
            librarian.markAsReturned(book);
            return;
        }

        librarian.notifyAsUnsuccessfulReturn();
    }
}

