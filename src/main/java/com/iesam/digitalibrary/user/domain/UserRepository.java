package com.iesam.digitalibrary.user.domain;

public interface UserRepository {


    void save(User user);


    User obtain(String userID);


    void delete(String userID);

    void modify(User user);
}
