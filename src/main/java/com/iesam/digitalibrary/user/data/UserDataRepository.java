package com.iesam.digitalibrary.user.data;


import com.iesam.digitalibrary.user.data.local.UserFileLocalDataSource;
import com.iesam.digitalibrary.user.data.local.UserLocalDataSource;
import com.iesam.digitalibrary.user.data.local.UserMemLocalDataSource;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;

import java.util.List;

public class UserDataRepository implements UserRepository {

     UserLocalDataSource userLocalDataSource;

    // Constructor to initialize with only one local data source
    public UserDataRepository(UserLocalDataSource userLocalDataSource) {
        this.userLocalDataSource = userLocalDataSource;
    }


    // Save a user using the appropriate local data source
    @Override
    public void save(User user) {
        userLocalDataSource.save(user);

    }

    // Delete a user by userID using the appropriate local data source
    @Override
    public void delete(String userID) {
        userLocalDataSource.delete(userID);

    }

    // Obtain a user by userID from the appropriate local data source
    @Override
    public User obtain(String userID) {
      return userLocalDataSource.findById(userID);
    }



    // Modify a user using the appropriate local data source
    @Override
    public void modify(User user) {
        userLocalDataSource.modify(user);
    }

    // List all users using the appropriate local data source
    @Override
    public List<User> list() {
        return userLocalDataSource.findAll();
    }
}




