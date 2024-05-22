package com.iesam.digitalibrary.digitalresources.ebook.data;

import com.iesam.digitalibrary.digitalresources.ebook.data.local.EbookResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;

public class EbookDataRepository implements EbookResourcesLocalDataSource {

    // Dependency on EbookResourcesLocalDataSource
    private EbookResourcesLocalDataSource ebookResourcesLocalDataSource;

    // Constructor to initialize with an EbookResourcesLocalDataSource
    public EbookDataRepository(EbookResourcesLocalDataSource ebookResourcesLocalDataSource) {
        this.ebookResourcesLocalDataSource = ebookResourcesLocalDataSource;
    }

    // Save an ebook using EbookResourcesLocalDataSource
    @Override
    public void save(Ebook ebook) {
        ebookResourcesLocalDataSource.save(ebook);
    }

    // Find an ebook by ID using EbookResourcesLocalDataSource
    @Override
    public Ebook findById(String idDigitalResource) {
        return ebookResourcesLocalDataSource.findById(idDigitalResource);
    }

    // Delete an ebook by ISBN using EbookResourcesLocalDataSource
    @Override
    public void delete(String isbn) {
        ebookResourcesLocalDataSource.delete(isbn);
    }

    // Modify an ebook using EbookResourcesLocalDataSource
    @Override
    public void modify(Ebook ebook) {
        ebookResourcesLocalDataSource.modify(ebook);
    }
}
