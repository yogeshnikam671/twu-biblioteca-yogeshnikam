package com.twu.biblioteca;

public class Movie {
    private final String name;
    private final String year;
    private final String director;
    private final String rating;

    Movie(String name, String year, String director, String rating){
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String getInfo(){
        return name + "\t" + year + "\t" + director + "\t" + rating;
    }
}
