package com.iesam.digitalibrary.digitalresources.ebook.domain;

public class ModifyEbookUseCase {

    // Dependency on EbookRepository
    private EbookRepository ebookRepository;

    // Constructor to initialize EbookRepository
    public ModifyEbookUseCase(EbookRepository ebookRepository) {
        this.ebookRepository = ebookRepository;
    }

    // Method to execute modifying an ebook
    void execute(Ebook ebook) {
        this.ebookRepository.modify(ebook); // Call modify method of EbookRepository to modify the ebook
    }

}
