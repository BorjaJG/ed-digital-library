package com.iesam.digitalibrary.digitalresources.ebook.domain;

public class DeleteEbookUseCase {

    // Dependency on EbookRepository
    private EbookRepository ebookRepository;

    // Constructor to initialize EbookRepository
    public DeleteEbookUseCase(EbookRepository ebookRepository) {
        this.ebookRepository = ebookRepository;
    }

    // Method to execute deleting an ebook by ISBN
    void execute(String isbn) {
        this.ebookRepository.delete(isbn); // Call delete method of EbookRepository to delete the ebook
    }

}
