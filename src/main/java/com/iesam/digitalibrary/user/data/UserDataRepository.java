package com.iesam.digitalibrary.user.data;


import com.iesam.digitalibrary.user.data.local.UserFileLocalDataSource;
import com.iesam.digitalibrary.user.data.local.UserLocalDataSource;
import com.iesam.digitalibrary.user.data.local.UserMemLocalDataSource;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserDataRepository implements UserRepository {


    private UserMemLocalDataSource memLocalDataSource;
    private UserFileLocalDataSource fileLocalDataSource;

    // Constructor to initialize with both memory and file local data sources
    public UserDataRepository(UserMemLocalDataSource memLocalDataSource, UserFileLocalDataSource fileLocalDataSource) {
        this.memLocalDataSource = memLocalDataSource;
        this.fileLocalDataSource = fileLocalDataSource;
    }

    private UserLocalDataSource userLocalDataSource;

    // Constructor to initialize with only one local data source
    public UserDataRepository(UserLocalDataSource userLocalDataSource) {
        this.userLocalDataSource = userLocalDataSource;
    }

    // Save a user using the appropriate local data source
    @Override
    public void save(User user) {
        userLocalDataSource.save(user);
    }

    // Obtain a user by userID from the appropriate local data source
    @Override
    public User obtain(String userID) {
        User userMem = memLocalDataSource.findById(userID);

        if (userMem != null) {
            return userMem; // If found in memory, return
        } else {
            userMem = fileLocalDataSource.findById(userID); // If not in memory, try finding in file
            memLocalDataSource.findById(userID); // Not sure why this line exists
            return userMem;
        }
    }

    // Delete a user by userID using the appropriate local data source
    @Override
    public void delete(String userID) {
        userLocalDataSource.delete(userID);
    }

    // Modify a user using the appropriate local data source
    @Override
    public void modify(User user) {
        userLocalDataSource.modify(user);
    }

    // List all users using the appropriate local data source
    @Override
    public List<User> list() {
        List<User> usersMem = memLocalDataSource.findAll();
        if (!usersMem.isEmpty()) {
            return usersMem; // If memory has users, return them
        } else {
            usersMem = fileLocalDataSource.findAll(); // If memory is empty, get users from file
            for (User user : usersMem) {
                memLocalDataSource.save(user); // Save users to memory for future use
            }
            return usersMem;
        }
    }
}




