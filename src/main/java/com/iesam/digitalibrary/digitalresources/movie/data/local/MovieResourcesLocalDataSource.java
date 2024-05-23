package com.iesam.digitalibrary.digitalresources.movie.data.local;



import com.iesam.digitalibrary.digitalresources.movie.domain.domain.Movie;


public interface MovieResourcesLocalDataSource {

    // Save a new ebook to the repository
    void save(Movie movie);

    // Find an ebook by its digital resource ID
    Movie findById(String idDigitalResource);

    // Delete an ebook from the repository by its ISBN
    void delete(String isbn);

    // Modify an existing ebook in the repository
    void modify(Movie movie);

}
