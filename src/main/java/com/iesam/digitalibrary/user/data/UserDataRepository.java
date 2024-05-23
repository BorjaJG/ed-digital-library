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

    public UserDataRepository(UserMemLocalDataSource memLocalDataSource, UserFileLocalDataSource fileLocalDataSource) {
        this.memLocalDataSource = memLocalDataSource;
        this.fileLocalDataSource = fileLocalDataSource;
    }

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
        User userMem=memLocalDataSource.findById(userID);

        if(userMem!=null){
            return userMem;
        }
        else {
            userMem=fileLocalDataSource.findById(userID);
            memLocalDataSource.findById(userID);
            return userMem;
        }

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
    public List<User> list() {
        List<User> usersMem=memLocalDataSource.findAll();
        if(!usersMem.isEmpty()){
            return usersMem;
        }else {
            usersMem=fileLocalDataSource.findAll();
            for (User user : usersMem) {
                memLocalDataSource.save(user);
            }
            return usersMem;
        }

    }
}




