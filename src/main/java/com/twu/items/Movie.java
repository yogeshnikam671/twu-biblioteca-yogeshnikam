package com.twu.items;

import java.util.Objects;

public class Movie implements Item {
    private final String name;
    private final String year;
    private String director;
    private String rating;

    public Movie(String name, String year, String director, String rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public Movie(String name, String year) {
        this.name = name;
        this.year = year;
    }

    public String getInfo() {
        return name + "\t" + year + "\t" + director + "\t" + rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return name.equals(movie.name) &&
                year.equals(movie.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year);
    }
}
