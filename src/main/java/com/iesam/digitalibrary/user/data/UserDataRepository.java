package com.iesam.digitalibrary.user.data;


import com.iesam.digitalibrary.user.data.local.UserLocalDataSource;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;

import java.util.ArrayList;

public class UserDataRepository implements UserRepository {

    private UserLocalDataSource userLocalDataSource;

    // Constructor to initialize with a UserLocalDataSource
    public UserDataRepository(UserLocalDataSource userLocalDataSource) {
        this.userLocalDataSource = userLocalDataSource;
    }

    // Save a user using the UserLocalDataSource
    @Override
    public void save(User user) {
        userLocalDataSource.save(user);
    }

    // Obtain a user by userID using the UserLocalDataSource
    @Override
    public User obtain(String userID) {
        return userLocalDataSource.findById(userID);
    }

    // Delete a user by userID using the UserLocalDataSource
    @Override
    public void delete(String userID) {
        userLocalDataSource.delete(userID);
    }

    // Modify a user using the UserLocalDataSource
    @Override
    public void modify(User user) {
        userLocalDataSource.modify(user);
    }

    // List all users using the UserLocalDataSource
    @Override
    public ArrayList<User> list() {
        return userLocalDataSource.findAll();
    }
}




