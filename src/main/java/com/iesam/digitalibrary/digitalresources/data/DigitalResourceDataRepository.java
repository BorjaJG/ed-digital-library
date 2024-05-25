package com.iesam.digitalibrary.digitalresources.data;

import com.iesam.digitalibrary.digitalresources.data.local.DigitalResourceResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResourceRepository;
import com.iesam.digitalibrary.digitalresources.ebook.data.local.EbookResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;

import java.util.ArrayList;
import java.util.List;


public class DigitalResourceDataRepository implements DigitalResourceRepository<DigitalResource> {

    public DigitalResourceResourcesLocalDataSource digitalResourceResourcesLocalDataSource;

    public DigitalResourceDataRepository(DigitalResourceResourcesLocalDataSource digitalResourceResourcesLocalDataSource) {
        this.digitalResourceResourcesLocalDataSource = digitalResourceResourcesLocalDataSource;
    }

    @Override
    public DigitalResource obtainDigitalResource(String idDigitalResource) {
        return digitalResourceResourcesLocalDataSource.findById(idDigitalResource);
    }

    @Override
    public List<DigitalResource> list() {
        return digitalResourceResourcesLocalDataSource.findAll();
    }

    @Override
    public void modify(DigitalResource digitalResource) {

    }

    @Override
    public void delete(String idDigitalResource) {
        digitalResourceResourcesLocalDataSource.delete(idDigitalResource);
    }

    @Override
    public void save(DigitalResource digitalResource) {
        digitalResourceResourcesLocalDataSource.save(digitalResource);
    }
}
