package com.iesam.digitalibrary.digitalresources.ebook.data;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResourceRepository;
import com.iesam.digitalibrary.digitalresources.ebook.data.local.EbookResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;

import java.util.List;


public class EbookDataRepository implements DigitalResourceRepository<Ebook> {

    public EbookResourcesLocalDataSource ebookResourcesLocalDataSource;

    public EbookDataRepository(EbookResourcesLocalDataSource ebookResourcesLocalDataSource) {
        this.ebookResourcesLocalDataSource = ebookResourcesLocalDataSource;
    }


    @Override
    public Ebook obtainDigitalResource(String idDigitalResource) {
        return null;
    }

    @Override
    public List<Ebook> list() {
        return null;
    }



    @Override
    public void modify(Ebook digitalResource) {
        ebookResourcesLocalDataSource.modify(digitalResource);
    }

    @Override
    public void delete(String idDigitalResource) {
        ebookResourcesLocalDataSource.findById(idDigitalResource);
    }

    @Override
    public void save(Ebook digitalResource) {
        ebookResourcesLocalDataSource.save(digitalResource);
    }


}
