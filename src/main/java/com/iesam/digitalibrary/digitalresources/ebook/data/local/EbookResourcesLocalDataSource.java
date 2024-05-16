package com.iesam.digitalibrary.digitalresources.ebook.data.local;


import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;
import com.iesam.digitalibrary.user.domain.User;

public interface EbookResourcesLocalDataSource {


    void save(Ebook ebook);
    Ebook findById(String idDigitalResource);
   void delete(String isbn);



}
