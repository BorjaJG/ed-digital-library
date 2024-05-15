package com.iesam.digitalibrary.digitalresources.ebook.data;

import com.iesam.digitalibrary.digitalresources.ebook.data.local.EbookResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;
import com.iesam.digitalibrary.user.data.local.UserLocalDataSource;

public class EbookDataRepository implements EbookResourcesLocalDataSource {

    EbookResourcesLocalDataSource ebookResourcesLocalDataSource;

    public EbookDataRepository(EbookResourcesLocalDataSource ebookResourcesLocalDataSource) {
        this.ebookResourcesLocalDataSource = ebookResourcesLocalDataSource;
    }

    @Override
    public void save(Ebook ebook) {
        ebookResourcesLocalDataSource.save(ebook);
    }
}
