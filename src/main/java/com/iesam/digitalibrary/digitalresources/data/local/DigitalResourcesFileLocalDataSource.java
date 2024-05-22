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


    private final String folderName = "dataStore";
    private final String fileName = "Ebook.txt";
    private final String filePath = folderName + File.separator + fileName;

    // Gson object for JSON serialization/deserialization
    private Gson gson = new Gson();

    // Type of list for Gson serialization/deserialization
    private final Type typeList = new TypeToken<ArrayList<DigitalResource>>() {
    }.getType();

    // Method to save a digital resource
    public boolean save(DigitalResource digitalResource) {
        // Get the list of digital resources
        List<DigitalResource> digitalResources = findAll();
        // Add the new digital resource
        digitalResources.add(digitalResource);
        // Save the updated list to the file
        saveToFile(digitalResources);
        return false;
    }

    // Method to save a list of digital resources
    public void saveList(List<DigitalResource> digitalResources) {
        // Save the list of digital resources to the file
        saveToFile(digitalResources);
    }

    // Method to save a list of digital resources to the file
    private void saveToFile(List<DigitalResource> digitalResources) {
        try {
            // Write the list of digital resources to the file
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(gson.toJson(digitalResources));
            myWriter.close();
            System.out.println("Data saved successfully");
        } catch (IOException e) {
            // Handle IO exception
            System.out.println("An error occurred while saving the data.");
            e.printStackTrace();
        }
    }

    // Method to find all digital resources
    public ArrayList<DigitalResource> findAll() {
        try {
            // Open the file to read data
            File myObj = new File(filePath);
            if (!myObj.exists()) {
                // Create the file if it doesn't exist
                myObj.createNewFile();
            }
            // Read data from the file
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                myReader.close();
                // Deserialize data into a list of digital resources
                return gson.fromJson(data, typeList);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.out.println("An error occurred while retrieving the list.");
            e.printStackTrace();
        } catch (IOException e) {
            // Handle IO exception
            System.out.println("An error occurred while creating the file.");
            throw new RuntimeException(e);
        }
        // Return an empty list if no data is found
        return new ArrayList<>();
    }

    // Method to find a digital resource by its ID
    @Override
    public DigitalResource findById(String id) {
        // Get the list of digital resources
        List<DigitalResource> digitalResources = findAll();
        // Iterate through the list to find the matching digital resource
        for (DigitalResource model : digitalResources) {
            if (Objects.equals(model.idDigitalResource, id)) {
                return model;
            }
        }
        // Return null if the digital resource is not found
        return null;
    }
}