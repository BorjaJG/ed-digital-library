package com.iesam.digitalibrary.user.data.local;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesam.digitalibrary.user.domain.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserFileLocalDataSource implements UserLocalDataSource {
    private final String folderName = "filestore";
    private final String fileName = "User.txt";
    private final String filePath = folderName + File.separator + fileName;
    private Gson gson = new Gson();
    private final Type typeList = new TypeToken<ArrayList<User>>() {
    }.getType();

    // Save a user to the file
    public void save(User user) {
        List<User> users = findAll();
        users.add(user);
        saveToFile(users);
    }

    // Save a list of users to the file
    public void saveList(List<User> users) {
        saveToFile(users);
    }

    // Internal method to save data to the file
    private void saveToFile(List<User> users) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(gson.toJson(users));
            myWriter.close();
            System.out.println("Data saved successfully");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data.");
            e.printStackTrace();
        }
    }

    // Find a user by ID
    public User findById(String id) {
        List<User> users = findAll();
        for (User user : users) {
            if (Objects.equals(user.userID, id)) {
                return user;
            }
        }
        return null;
    }

    // Find all users from the file
    public List<User> findAll() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                myReader.close();
                return gson.fromJson(data, typeList);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while retrieving the list.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }

    // Delete a user by ID
    public void delete(String userId) {
        List<User> newList = new ArrayList<>();
        List<User> users = findAll();
        for (User user : users) {
            if (!user.userID.equals(userId)) {
                newList.add(user);
            }
        }
        saveList(newList);
    }

    // Modify a user
    public void modify(User user) {
        delete(user.userID); // Delete the user with the given ID
        save(user); // Save the modified user
    }


}