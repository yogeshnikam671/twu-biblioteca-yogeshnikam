package com.twu.biblioteca;

import java.util.Objects;
import java.util.Scanner;

public class Book {
    private final String title;
    private final String author;
    private final int year;

    public Book(String bookTitle, String author, int year) {
        this.title = bookTitle;
        this.author = author;
        this.year = year;
    }

    public String getInfo(){
        return title + "\t" + author + "\t" + year;
    }

    public static Book getInputtedBook() {
        Scanner scanner = new Scanner(System.in);
        String title = scanner.next();
        String author = scanner.next();
        int year = scanner.nextInt();

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

    public String getTitle() {
        return title;
    }
}
