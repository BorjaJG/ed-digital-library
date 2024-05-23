package com.iesam.digitalibrary.digitalresources.movie.domain.domain;


import com.iesam.Main;

public class GetMovieUseCase {

    // Dependency on MovieRepository
    private MovieRepository movieRepository;

    // Constructor to initialize MovieRepository
    public GetMovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Method to execute obtaining a movie by ISBN
    Movie execute(String isbn) {
        return this.movieRepository.obtain(isbn); // Call obtain method of MovieRepository to obtain the ebook
    }
}
