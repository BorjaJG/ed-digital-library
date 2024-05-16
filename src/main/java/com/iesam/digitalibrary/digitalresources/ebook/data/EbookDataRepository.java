package com.iesam.digitalibrary.digitalresources.ebook.data;

import com.iesam.digitalibrary.digitalresources.ebook.data.local.EbookResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;

public class EbookDataRepository implements EbookResourcesLocalDataSource {

    EbookResourcesLocalDataSource ebookResourcesLocalDataSource;

    public EbookDataRepository(EbookResourcesLocalDataSource ebookResourcesLocalDataSource) {
        this.ebookResourcesLocalDataSource = ebookResourcesLocalDataSource;
    }

    @Override
    public void save(Ebook ebook) {
        ebookResourcesLocalDataSource.save(ebook);
    }

    @Override
    public Ebook findById(String idDigitalResource) {
        return  ebookResourcesLocalDataSource.findById(idDigitalResource);
    }

    @Override
    public void delete(String isbn) {
         ebookResourcesLocalDataSource.delete(isbn);
    }

    @Override
    public void modify(Ebook ebook) {
        ebookResourcesLocalDataSource.modify(ebook);
    }


}
