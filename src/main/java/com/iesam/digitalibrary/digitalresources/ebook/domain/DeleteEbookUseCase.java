package com.iesam.digitalibrary.digitalresources.ebook.domain;

public class DeleteEbookUseCase {

    public EbookRepository ebookRepository;


    public DeleteEbookUseCase(EbookRepository ebookRepository) {
        this.ebookRepository = ebookRepository;
    }

    void execute(String isbn){
        this.ebookRepository.delete(isbn);

    }


}
