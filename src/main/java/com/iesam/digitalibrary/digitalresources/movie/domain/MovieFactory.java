package com.iesam.digitalibrary.digitalresources.movie.domain;

import com.iesam.digitalibrary.user.domain.User;

public class MovieFactory {
    public Movie build(String idDigitalResource, String isbn, String title, String director, String publicationDate) {
        return new Movie(idDigitalResource, isbn, title, director, publicationDate);
    }
}
