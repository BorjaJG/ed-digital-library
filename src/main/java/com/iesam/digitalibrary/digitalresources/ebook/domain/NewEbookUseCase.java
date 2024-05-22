package com.iesam.digitalibrary.digitalresources.ebook.domain;

public class NewEbookUseCase {
    // Dependency on EbookRepository
    private EbookRepository ebookRepository;

    // Constructor to initialize EbookRepository
    public NewEbookUseCase(EbookRepository ebookRepository) {
        this.ebookRepository = ebookRepository;
    }

    // Method to execute saving a new ebook
    void execute(Ebook ebook) {
        this.ebookRepository.save(ebook); // Call save method of EbookRepository to save the new ebook
    }
}
