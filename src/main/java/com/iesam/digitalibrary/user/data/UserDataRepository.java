package com.iesam.digitalibrary.user.data;


import com.iesam.digitalibrary.user.data.local.UserLocalDataSource;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;

public class UserDataRepository implements UserRepository {

     UserLocalDataSource userLocalDataSource;

    public UserDataRepository(UserLocalDataSource userLocalDataSource) {
        this.userLocalDataSource = userLocalDataSource;
    }

    @Override
    public void save(User user) {
        userLocalDataSource.save(user);
    }

    @Override
    public User obtain(String userID) {
        return userLocalDataSource.findById(userID);
    }

    @Override
    public void delete(String userID) {
        userLocalDataSource.delete(userID);
    }


}




