package com.iesam.digitalibrary.digitalresources.ebook.data.local;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;


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
    private final String folderName = "dataStore";
    private final String fileName = "Ebook.txt";
    private final String filePath = folderName + File.separator + fileName;

    // Gson instance for JSON serialization and deserialization
    private Gson gson = new Gson();

    // TypeToken for deserializing a list of ebooks
    private final Type typeList = new TypeToken<ArrayList<Ebook>>() {}.getType();

    // Save an ebook to the file
    @Override
    public void save(Ebook ebook) {
        List<Ebook> ebooks = findAll();
        ebooks.add(ebook);
        saveToFile(ebooks);
    }

    // Save a list of ebooks to the file
    public void saveList(List<Ebook> ebooks) {
        saveToFile(ebooks);
    }

    // Helper method to save the list of ebooks to the file
    private void saveToFile(List<Ebook> ebooks) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(gson.toJson(ebooks));
            myWriter.close();
            System.out.println("Datos guardados correctamente");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la información.");
            e.printStackTrace();
        }
    }

    // Find an ebook by its ID (ISBN in this case)
    @Override
    public Ebook findById(String id) {
        List<Ebook> ebooks = findAll();
        for (Ebook model : ebooks) {
            if (Objects.equals(model.idDigitalResource, id)) {
                return model;
            }
        }
        return null;
    }

    // Find all ebooks in the file
    public ArrayList<Ebook> findAll() {
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

    // Delete an ebook by its ID (ISBN in this case)
    @Override
    public void delete(String isbn) {
        List<Ebook> newList = new ArrayList<>();
        List<Ebook> models = findAll();
        for (Ebook model : models) {
            if (!model.isbn.equals(isbn)) {
                newList.add(model);
            }
        }
        saveList(newList);
    }

    // Modify an existing ebook in the file
    @Override
    public void modify(Ebook ebook) {
        delete(ebook.isbn);
        save(ebook);
    }

}