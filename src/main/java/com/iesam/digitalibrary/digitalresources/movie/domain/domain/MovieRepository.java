package com.iesam.digitalibrary.digitalresources.movie.domain.domain;

public interface MovieRepository {


    void modify(Movie movie);

    void save(Movie movie);

    Movie obtain(String isbn);

    void delete(String isbn);
}
