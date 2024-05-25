package com.iesam.digitalibrary.digitalresources.data.local;


import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;
import com.iesam.digitalibrary.user.domain.User;

import java.util.List;


public interface DigitalResourceResourcesLocalDataSource {

    // Save a new ebook to the repository
    void save(DigitalResource digitalResource);

    // Find an ebook by its digital resource ID
    DigitalResource findById(String idDigitalResource);

    // Delete an ebook from the repository by its ISBN
    void delete(String idDigitalResource);

    // Modify an existing ebook in the repository
    void modify(DigitalResource digitalResource);

    List<DigitalResource> findAll();

}
