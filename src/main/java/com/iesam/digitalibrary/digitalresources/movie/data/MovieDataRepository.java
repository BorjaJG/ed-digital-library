package com.iesam.digitalibrary.digitalresources.movie.data;

import com.iesam.digitalibrary.digitalresources.movie.data.local.MovieResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.movie.domain.domain.Movie;

public class MovieDataRepository implements MovieResourcesLocalDataSource {

    // Dependency on EbookResourcesLocalDataSource
    private MovieResourcesLocalDataSource movieResourcesLocalDataSource;

    // Constructor to initialize with an EbookResourcesLocalDataSource
    public MovieDataRepository(MovieResourcesLocalDataSource movieResourcesLocalDataSource) {
        this.movieResourcesLocalDataSource = movieResourcesLocalDataSource;
    }

    // Save an ebook using EbookResourcesLocalDataSource
    @Override
    public void save(Movie movie) {
        movieResourcesLocalDataSource.save(movie);
    }

    // Find an ebook by ID using EbookResourcesLocalDataSource
    @Override
    public Movie findById(String idDigitalResource) {
        return movieResourcesLocalDataSource.findById(idDigitalResource);
    }

    // Delete an ebook by ISBN using EbookResourcesLocalDataSource
    @Override
    public void delete(String isbn) {
        movieResourcesLocalDataSource.delete(isbn);
    }

    // Modify an ebook using EbookResourcesLocalDataSource
    @Override
    public void modify(Movie movie) {
        movieResourcesLocalDataSource.modify(movie);
    }
}
