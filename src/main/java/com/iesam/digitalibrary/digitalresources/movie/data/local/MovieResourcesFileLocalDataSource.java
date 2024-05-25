package com.iesam.digitalibrary.digitalresources.movie.data.local;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;
import com.iesam.digitalibrary.digitalresources.movie.domain.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MovieResourcesFileLocalDataSource implements MovieResourcesLocalDataSource {
    private final String folderName = "filestore";
    private final String fileName = "DigitalResource.txt";
    private final String filePath = folderName + File.separator + fileName;

    // Gson instance for JSON serialization and deserialization
    private Gson gson = new Gson();

    // TypeToken for deserializing a list of ebooks
    private final Type typeList = new TypeToken<ArrayList<Ebook>>() {}.getType();

    // Save an ebook to the file
    @Override
    public void save(Movie movie) {
        List<Movie> movies = findAll();
        movies.add(movie);
        saveToFile(movies);
    }

    // Save a list of ebooks to the file
    public void saveList(List<Movie> movies) {
        saveToFile(movies);
    }

    // Helper method to save the list of ebooks to the file
    private void saveToFile(List<Movie> movies) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(gson.toJson(movies));
            myWriter.close();
            System.out.println("Datos guardados correctamente");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la información.");
            e.printStackTrace();
        }
    }

    // Find an ebook by its ID (ISBN in this case)
    @Override
    public Movie findById(String id) {
        List<Movie> movies = findAll();
        for (Movie model : movies) {
            if (Objects.equals(model.idDigitalResource, id)) {
                return model;
            }
        }
        return null;
    }

    // Find all ebooks in the file
    public ArrayList<Movie> findAll() {
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
    public void delete(String modelCode) {
        List<Movie> newList = new ArrayList<>();
        List<Movie> models = findAll();
        for (Movie model : models) {
            if (!model.idDigitalResource.equals(modelCode)) {
                newList.add(model);
            }
        }
        saveList(newList);
    }

    // Modify an existing ebook in the file
    @Override
    public void modify(Movie movie) {
        delete(movie.idDigitalResource);
        save(movie);
    }

}