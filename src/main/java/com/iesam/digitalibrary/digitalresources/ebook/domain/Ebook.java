package com.iesam.digitalibrary.digitalresources.ebook.domain;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;

public class Ebook extends DigitalResource {
    public final String title;
    public final String author;
    public final String publicationDate;
    public final String isbn;

    public Ebook(String idDigitalResource, String title, String author, String publicationDate, String isbn) {
        super(idDigitalResource);
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.isbn = idDigitalResource;
    }

    @Override
    public String toString() {
        return "Ebook{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", isbn='" + isbn + '\'' +
                ", idDigitalResource='" + idDigitalResource + '\'' +
                "} " + super.toString();
    }
}
