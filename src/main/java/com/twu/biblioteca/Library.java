package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private Librarian librarian;

    public Library() {
        books.add(new Book("A", "Charles", 2015));   // pre-existing books
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
        int i = 1;
        for (Book book : books) {
            System.out.println(i + ". " + book.getTitle());
            i++;
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
}

