package com.iesam.digitalibrary.digitalresources.ebook.domain;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;

public class Ebook extends DigitalResource {
    // Attributes specific to Ebook
    public final String title;
    public final String author;
    public final String publicationDate;

    public final String isbn;

    // Constructor to initialize Ebook attributes
    public Ebook(String idDigitalResource, String title, String author, String publicationDate, String isbn) {
        super(idDigitalResource); // Call the constructor of the superclass
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
    }

    // toString method to represent Ebook object as a String
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
