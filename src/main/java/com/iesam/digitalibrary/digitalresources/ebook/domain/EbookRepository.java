package com.iesam.digitalibrary.digitalresources.ebook.domain;

public interface EbookRepository {

    void save(Ebook ebook);

    Ebook obtain(String isbn);

    void delete(String isbn);

    void modify(Ebook ebook);
}
