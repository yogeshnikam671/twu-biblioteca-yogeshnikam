package com.twu.biblioteca;

import java.util.Objects;

public class Book {

    private final String title;
    private final String author;
    private final int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getInfo() {
        return title + "\t" + author + "\t" + year;
    }

    public static Book getQueriedBook(String title, String author, int year) {
        return new Book(title, author, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) && author.equals(book.author) && year == book.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
