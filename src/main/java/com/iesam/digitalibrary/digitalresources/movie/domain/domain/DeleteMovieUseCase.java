package com.iesam.digitalibrary.digitalresources.movie.domain.domain;

public class DeleteMovieUseCase {

    // Dependency on MovieRepository
    private MovieRepository movieRepository;

    // Constructor to initialize MovieRepository
    public DeleteMovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Method to execute deleting a Movie by ISBN
    void execute(String isbn) {
        this.movieRepository.delete(isbn); // Call delete method of EbookRepository to delete the Movie
    }

}
