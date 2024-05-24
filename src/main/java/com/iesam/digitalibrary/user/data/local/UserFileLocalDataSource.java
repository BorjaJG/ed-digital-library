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
    private final Gson gson = new Gson();
    private final Type typeList = new TypeToken<ArrayList<User>>() {
    }.getType();
    public void save(User model) {
        List<User> models = findAll();
        models.add(model);
        saveToFile(models);
    }

    @Override
    public User findById(String code) {
        List<User> models = findAll();
        for (User model : models) {
            if (Objects.equals(model.userID, code)) {
                return model;
            }
        }
        return null;
    }

    public void saveList(List<User> models) {
        saveToFile(models);
    }

    private void saveToFile(List<User> models) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(gson.toJson(models));
            myWriter.close();
            System.out.println("Datos guardados correctamente");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la información.");
            e.printStackTrace();
        }
    }


    public List<User> findAll() {
        try {
            File myObj = new File(filePath);
            if (!myObj.exists()) {
                myObj.createNewFile();
            }
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                myReader.close();
                return gson.fromJson(data, typeList);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un error al obtener el listado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al crear el fichero.");
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }

    public void delete(String modelCode) {
        List<User> newList = new ArrayList<>();
        List<User> models = findAll();
        for (User model : models) {
            if (!model.userID.equals(modelCode)) {
                newList.add(model);
            }
        }
        saveList(newList);
    }

    @Override
    public void modify(User user) {
        delete(user.userID);
        save(user);
    }


}