package com.iesam.digitalibrary.digitalresources.movie.data.local;


import com.iesam.digitalibrary.digitalresources.movie.domain.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MovieResourcesMemLocalDataSource {
    // In-memory storage for ebooks
    private Map<String, Movie> dataStore = new TreeMap<>();
    // Singleton instance
    private static MovieResourcesMemLocalDataSource instance = null;

    // Method to get a singleton instance
    public static MovieResourcesMemLocalDataSource newInstance() {
        if (instance == null) {
            instance = new MovieResourcesMemLocalDataSource();
        }
        return instance;
    }

    // Save a Movie to the data store
    public void save(Movie movie) {
        dataStore.put(movie.isbn, movie);
    }

    // Save a list of Movies to the data store
    public void saveList(List<Movie> movies) {
        for (Movie movie : movies) {
            save(movie);
        }
    }

    // Find a Movie by its ID (ISBN in this case)
    public Movie findById(String idMovie) {
        return dataStore.get(idMovie);
    }

    // Find all Movies in the data store
    public List<Movie> findAll() {
        return new ArrayList<>(dataStore.values());
    }

    // Delete a Movie by its ID (ISBN in this case)
    public void delete(String idMovie) {
        dataStore.remove(idMovie);
    }
}
