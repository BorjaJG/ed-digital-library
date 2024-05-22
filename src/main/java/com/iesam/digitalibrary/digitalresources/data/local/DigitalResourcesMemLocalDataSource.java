package com.iesam.digitalibrary.digitalresources.data.local;


import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DigitalResourcesMemLocalDataSource {

    // Data store for digital resources
    private Map<String, DigitalResource> dataStore = new TreeMap<>();

    // Singleton instance
    private static DigitalResourcesMemLocalDataSource instance = null;

    // Private constructor to prevent external instantiation
    private DigitalResourcesMemLocalDataSource() {
    }

    // Method to create a new instance of the data source or return the existing one
    public static DigitalResourcesMemLocalDataSource newInstance() {
        if (instance == null) {
            instance = new DigitalResourcesMemLocalDataSource();
        }
        return instance;
    }

    // Method to save a digital resource
    public void save(DigitalResource digitalResource) {
        dataStore.put(digitalResource.idDigitalResource, digitalResource); // Add the digital resource to the data store
    }

    // Method to save a list of digital resources
    public void saveList(List<DigitalResource> digitalResources) {
        for (DigitalResource digitalResource : digitalResources) {
            save(digitalResource); // Save each digital resource in the list
        }
    }

    // Method to find a digital resource by its ID
    public DigitalResource findById(String idDigitalResource) {
        return dataStore.get(idDigitalResource); // Retrieve the digital resource from the data store by its ID
    }

    // Method to find all digital resources
    public List<DigitalResource> findAll() {
        return new ArrayList<>(dataStore.values()); // Return all digital resources from the data store as a list
    }

    // Method to delete a digital resource by its ID
    public void delete(String idDigitalResource) {
        dataStore.remove(idDigitalResource); // Remove the digital resource from the data store by its ID
    }
}
