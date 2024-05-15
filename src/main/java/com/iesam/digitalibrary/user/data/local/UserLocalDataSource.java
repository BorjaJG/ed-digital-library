package com.iesam.digitalibrary.user.data.local;


import com.iesam.digitalibrary.user.domain.User;

public interface UserLocalDataSource {


    void save(User user);
    User findById(String userId);

    void delete(String userID);




}
