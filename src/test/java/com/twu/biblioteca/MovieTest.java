package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    @Test
    void shouldGetInformationOfMovie() {
        Movie movie = new Movie("Dabangg", "2016", "Rajesh","10");
        String expectedInfo = "Dabangg"+ "\t" +"2016"+ "\t" +"Rajesh"+ "\t" +"10";

        assertEquals(expectedInfo, movie.getInfo());
    }
}