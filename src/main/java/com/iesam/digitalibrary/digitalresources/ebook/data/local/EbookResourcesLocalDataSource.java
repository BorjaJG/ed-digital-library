package com.iesam.digitalibrary.digitalresources.ebook.data.local;


import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;
import com.iesam.digitalibrary.digitalresources.movie.domain.Movie;

import java.util.List;


public interface EbookResourcesLocalDataSource {

    // Save a new ebook to the repository
    void save(Ebook ebook);

    // Find an ebook by its digital resource ID
    Ebook findById(String idDigitalResource);

    // Delete an ebook from the repository by its ISBN
    void delete(String idDigitalResource);

    // Modify an existing ebook in the repository
    void modify(Ebook ebook);
    List<Ebook> findAll();

}
