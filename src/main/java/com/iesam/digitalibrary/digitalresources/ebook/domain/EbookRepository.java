package com.iesam.digitalibrary.digitalresources.ebook.domain;

public interface EbookRepository {

    // Save an ebook
    void save(Ebook ebook);

    // Obtain an ebook by ISBN
    Ebook obtain(String isbn);

    // Delete an ebook by ISBN
    void delete(String isbn);

    // Modify an ebook
    void modify(Ebook ebook);
}
