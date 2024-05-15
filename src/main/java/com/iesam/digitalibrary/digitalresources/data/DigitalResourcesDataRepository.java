package com.iesam.digitalibrary.digitalresources.data;

import com.iesam.digitalibrary.digitalresources.data.local.DigitalResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.digitalresources.ebook.data.local.EbookResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;

import java.util.ArrayList;

public class DigitalResourcesDataRepository implements DigitalResourcesLocalDataSource {

    DigitalResourcesLocalDataSource digitalResourcesLocalDataSource;


    public DigitalResourcesDataRepository(DigitalResourcesLocalDataSource digitalResourcesLocalDataSource) {
        this.digitalResourcesLocalDataSource = digitalResourcesLocalDataSource;
    }


    @Override
    public ArrayList<DigitalResource> findAll() {
        return digitalResourcesLocalDataSource.findAll();
    }
}
