package com.iesam.digitalibrary.digitalresources.ebook.domain;


public class GetEbookUseCase {

    // Dependency on EbookRepository
    private EbookRepository ebookRepository;

    // Constructor to initialize EbookRepository
    public GetEbookUseCase(EbookRepository ebookRepository) {
        this.ebookRepository = ebookRepository;
    }

    // Method to execute obtaining an ebook by ISBN
    Ebook execute(String isbn) {
        return this.ebookRepository.obtain(isbn); // Call obtain method of EbookRepository to obtain the ebook
    }
}
