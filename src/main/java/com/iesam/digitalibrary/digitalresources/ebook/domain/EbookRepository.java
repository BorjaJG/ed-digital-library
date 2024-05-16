package com.iesam.digitalibrary.digitalresources.ebook.domain;

public interface EbookRepository {

    void save(Ebook ebook);

    Ebook obtain(String isbn);
}
