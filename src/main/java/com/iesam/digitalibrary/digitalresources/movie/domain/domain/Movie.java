package com.iesam.digitalibrary.digitalresources.movie.domain.domain;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;

public class Movie extends DigitalResource {
    // Attributes specific to Movie
    public final String title;
    public final String director;
    public final String publicationDate;

    public final String isbn;

    // Constructor to initialize Movie attributes
    public Movie(String idDigitalResource, String title, String director, String publicationDate, String isbn) {
        super(idDigitalResource); // Call the constructor of the superclass
        this.title = title;
        this.director = director;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
    }

    // toString method to represent Movie object as a String
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", isbn='" + isbn + '\'' +
                ", idDigitalResource='" + idDigitalResource + '\'' +
                "} " + super.toString();
    }
}
