package com.iesam.digitalibrary.digitalresources.data.local;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class DigitalResourcesFileLocalDataSource implements DigitalResourcesLocalDataSource {

    private String nameFile = "Ebook.txt";

    private Gson gson = new Gson();

    private final Type typeList = new TypeToken<ArrayList<DigitalResource>>() {
    }.getType();

    public boolean save(DigitalResource digitalResource) {
        List<DigitalResource> digitalResources = findAll();
        digitalResources.add(digitalResource);
        saveToFile(digitalResources);
        return false;
    }

    public void saveList(List<DigitalResource> digitalResources) {
        saveToFile(digitalResources);
    }

    private void saveToFile(List<DigitalResource> digitalResources) {
        try {
            FileWriter myWriter = new FileWriter(nameFile);
            myWriter.write(gson.toJson(digitalResources));
            myWriter.close();
            System.out.println("Datos guardados correctamente");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la información.");
            e.printStackTrace();
        }
    }

    public DigitalResource findById(String id) {
        List<DigitalResource> digitalResources = findAll();
        for (DigitalResource model : digitalResources) {
            if (Objects.equals(model.idDigitalResource, id)) {
                return model;
            }
        }
        return null;
    }

    public ArrayList<DigitalResource> findAll() {
        try {
            File myObj = new File(nameFile);
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




}