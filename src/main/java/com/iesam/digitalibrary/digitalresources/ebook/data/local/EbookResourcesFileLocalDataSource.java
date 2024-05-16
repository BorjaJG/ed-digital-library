package com.iesam.digitalibrary.digitalresources.ebook.data.local;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;
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
public class EbookResourcesFileLocalDataSource implements EbookResourcesLocalDataSource {

    private String nameFile = "Ebook.txt";

    private Gson gson = new Gson();

    private final Type typeList = new TypeToken<ArrayList<Ebook>>() {
    }.getType();

    public void save(Ebook ebook) {
        List<Ebook> ebooks = findAll();
        ebooks.add(ebook);
        saveToFile(ebooks);

    }

    public void saveList(List<Ebook> ebooks) {
        saveToFile(ebooks);
    }

    private void saveToFile(List<Ebook> ebooks) {
        try {
            FileWriter myWriter = new FileWriter(nameFile);
            myWriter.write(gson.toJson(ebooks));
            myWriter.close();
            System.out.println("Datos guardados correctamente");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la información.");
            e.printStackTrace();
        }
    }

    public Ebook findById(String id) {
        List<Ebook> ebooks = findAll();
        for (Ebook model : ebooks) {
            if (Objects.equals(model.idDigitalResource, id)) {
                return model;
            }
        }
        return null;
    }

    public ArrayList<Ebook> findAll() {
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

    public void delete(String isbn) {
        List<Ebook> newList = new ArrayList<>();
        List<Ebook> models = findAll();
        for (Ebook model : models) {
            if (!model.isbn.equals(isbn)){
                newList.add(model);
            }
        }
        saveList(newList);
    }




}