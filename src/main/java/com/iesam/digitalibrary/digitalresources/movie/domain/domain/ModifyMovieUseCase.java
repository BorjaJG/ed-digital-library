package com.iesam.digitalibrary.digitalresources.movie.domain.domain;

public class ModifyMovieUseCase {

    // Dependency on EbookRepository
    private MovieRepository movieRepository;

    // Constructor to initialize EbookRepository
    public ModifyMovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Method to execute modifying an ebook
    void execute(Movie movie) {
        this.movieRepository.modify(movie); // Call modify method of EbookRepository to modify the ebook
    }

}
