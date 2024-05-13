package com.iesam.digitalibrary.user.data.local;



import com.iesam.digitalibrary.user.domain.User;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserMemLocalDataSource {

    private Map<String, User> dataStore = new TreeMap<>();
    private static UserMemLocalDataSource instance=null;
    public UserMemLocalDataSource newInstance(){
        if(instance== null){
            instance= new UserMemLocalDataSource();
        }
        return instance;
    }

    public void save(User user) {
        dataStore.put(user.userID, user);
    }

    public void saveList(List<User> users) {
        for (User user : users) {
            save(user);
        }
    }

    public User findById(String userId) {
        return dataStore.get(userId);
    }

    public List<User> findAll() {
        return dataStore.values().stream().toList();
    }

    public void delete(String UserId) {
        dataStore.remove(UserId);
    }
}
