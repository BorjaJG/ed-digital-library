package com.iesam.digitalibrary.digitalresources.movie.domain.domain;

public class NewMovieUseCase {
    // Dependency on EbookRepository
    private MovieRepository movieRepository;

    // Constructor to initialize EbookRepository
    public NewMovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Method to execute saving a new ebook
    void execute(Movie movie) {
        this.movieRepository.save(movie); // Call save method of EbookRepository to save the new ebook
    }
}
