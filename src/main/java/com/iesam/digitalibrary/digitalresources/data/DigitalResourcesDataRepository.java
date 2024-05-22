package com.iesam.digitalibrary.digitalresources.data;

import com.iesam.digitalibrary.digitalresources.data.local.DigitalResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.digitalresources.ebook.data.local.EbookResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;

import java.util.ArrayList;

public class DigitalResourcesDataRepository implements DigitalResourcesLocalDataSource {

    // Local data source for digital resources
    private DigitalResourcesLocalDataSource digitalResourcesLocalDataSource;

    // Constructor to initialize the local data source
    public DigitalResourcesDataRepository(DigitalResourcesLocalDataSource digitalResourcesLocalDataSource) {
        this.digitalResourcesLocalDataSource = digitalResourcesLocalDataSource;
    }

    // Method to find all digital resources
    @Override
    public ArrayList<DigitalResource> findAll() {
        return digitalResourcesLocalDataSource.findAll(); // Retrieve all digital resources from the local data source
    }

    // Method to find a digital resource by its ID
    @Override
    public DigitalResource findById(String id) {
        return digitalResourcesLocalDataSource.findById(id); // Find a digital resource by its ID in the local data source
    }
}
