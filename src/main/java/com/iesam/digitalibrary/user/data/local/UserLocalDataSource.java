package com.iesam.digitalibrary.user.data.local;


import com.iesam.digitalibrary.user.domain.User;

import java.util.ArrayList;

public interface UserLocalDataSource {


    // Save a user
    void save(User user);

    // Find a user by ID
    User findById(String userId);

    // Delete a user by ID
    void delete(String userId);

    // Modify a user
    void modify(User user);

    // Find all users
    ArrayList<User> findAll();
}
