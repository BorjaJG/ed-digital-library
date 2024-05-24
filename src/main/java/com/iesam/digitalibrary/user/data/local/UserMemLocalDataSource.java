package com.iesam.digitalibrary.user.data.local;


import com.iesam.digitalibrary.user.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserMemLocalDataSource implements UserLocalDataSource{

    // Data store to hold users
     Map<String, User> dataStore = new TreeMap<>();

    // Singleton instance
    private static UserMemLocalDataSource instance = null;


    // Method to create a new instance of UserMemLocalDataSource (singleton pattern)
    public static UserMemLocalDataSource newInstance() {
        if (instance == null) {
            instance = new UserMemLocalDataSource();
        }
        return instance;
    }

    // Save a user to the data store
    public void save(User user) {
        dataStore.put(user.userID, user);
    }

    // Save a list of users to the data store
    public void saveList(List<User> users) {
        for (User user : users) {
            save(user);
        }
    }

    // Find a user by ID
    public User findById(String userId) {
        return dataStore.get(userId);
    }

    // Find all users in the data store
    public List<User> findAll() {
        return new ArrayList<>(dataStore.values());
    }

    // Delete a user by ID
    public void delete(String userId) {
        dataStore.remove(userId);
    }

    @Override
    public void modify(User user) {
        delete(user.userID);
        save(user);
    }
}