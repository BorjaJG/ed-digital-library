package com.iesam.digitalibrary.digitalresources.ebook.domain;

public class Ebook {
    public final String title;
    public final String author;
    public final String publicationDate;
    public final String isbn;


    public Ebook(String title, String author, String publicationDate, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Ebook{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
