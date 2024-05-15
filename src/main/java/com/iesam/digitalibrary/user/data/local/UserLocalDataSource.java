package com.iesam.digitalibrary.user.data.local;


import com.iesam.digitalibrary.user.domain.User;

import java.util.ArrayList;

public interface UserLocalDataSource {


    void save(User user);
    User findById(String userId);

    void delete(String userID);


    void modify(User user);

    ArrayList<User> findAll();
}
