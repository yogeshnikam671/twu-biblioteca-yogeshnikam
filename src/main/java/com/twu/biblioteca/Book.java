package com.twu.biblioteca;

import java.util.Objects;

public class Book implements Item {

    private final String title;
    private final String author;
    private final String year;

    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getInfo() {
        return title + "\t" + author + "\t" + year;
    }

    public static Book getQueriedBook(String title, String author, String year) {
        return new Book(title, author, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) && author.equals(book.author) && year.equals(book.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
