package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void shouldBeAbleToGetComparedWithAnotherUser() {
        String libraryNumber = "123-4567";
        String password = "1234";
        User user1 = new User(libraryNumber, password);

        User user2 = new User("345-6789", "pass");

        assertNotEquals(user1, user2);
    }
}