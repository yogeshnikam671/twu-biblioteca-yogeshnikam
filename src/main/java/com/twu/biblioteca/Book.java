package com.twu.biblioteca;

import java.util.Objects;
import java.util.Scanner;

public class Book {
    //TODO: class structure
        //declaration
        //construct
        //public
        //auto gen
        //privates

    private final String title;
    private final String author;
    private final int year;

    public Book(String bookTitle, String author, int year) {
        this.title = bookTitle;
        this.author = author;
        this.year = year;
    } //TODO: no need to repeat book. title is sufficient

    public String getInfo(){
        return title + "\t" + author + "\t" + year;
    } // TODO: why no test.

    public static Book getInputtedBook() { // TODO: better name
        Scanner scanner = new Scanner(System.in);
        String title = scanner.next();
        String author = scanner.next();
        int year = Integer.parseInt( scanner.next() ); // TODO: static imports

        return new Book(title, author, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public String getTitle() { //TODO: not needed
        return title;
    }
}
