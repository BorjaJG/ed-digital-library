package com.iesam.digitalibrary.digitalresources.ebook.domain;

import com.iesam.digitalibrary.digitalresources.movie.domain.Movie;

public class EbookFactory {
    public Ebook build(String idDigitalResource, String isbn, String title, String author, String publicationDate) {
        return new Ebook(idDigitalResource, isbn, title, author, publicationDate);
    }
}
