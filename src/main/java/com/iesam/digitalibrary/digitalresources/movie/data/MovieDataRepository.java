package com.iesam.digitalibrary.digitalresources.movie.data;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResourceRepository;
import com.iesam.digitalibrary.digitalresources.movie.data.local.MovieResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.movie.domain.Movie;

import java.util.List;

public class MovieDataRepository implements DigitalResourceRepository<Movie> {
    private final MovieResourcesLocalDataSource movieResourcesLocalDataSource;

    public MovieDataRepository(MovieResourcesLocalDataSource movieResourcesLocalDataSource) {
        this.movieResourcesLocalDataSource = movieResourcesLocalDataSource;
    }

    @Override
    public Movie obtainDigitalResource(String idDigitalResource) {
        return movieResourcesLocalDataSource.findById(idDigitalResource);
    }

    @Override
    public List<Movie> list() {
        return  movieResourcesLocalDataSource.findAll();
    }

    @Override
    public void modify(Movie digitalResource) {
        movieResourcesLocalDataSource.modify(digitalResource);
    }

    @Override
    public void delete(String idDigitalResource) {
        movieResourcesLocalDataSource.findById(idDigitalResource);
    }

    @Override
    public void save(Movie digitalResource) {
        movieResourcesLocalDataSource.save(digitalResource);
    }
}