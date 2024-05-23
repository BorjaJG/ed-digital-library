package com.iesam.digitalibrary.user.domain;

import java.util.List;

public interface UserRepository {
    // Method to save a user
    void save(User user);

    // Method to obtain a user by userID
    User obtain(String userID);

    // Method to delete a user by userID
    void delete(String userID);

    // Method to modify a user
    void modify(User user);

    // Method to list all users
    List<User> list();
}