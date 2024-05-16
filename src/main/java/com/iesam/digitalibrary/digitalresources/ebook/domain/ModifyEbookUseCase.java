package com.iesam.digitalibrary.digitalresources.ebook.domain;

public class ModifyEbookUseCase {

    public EbookRepository ebookRepository;

    public ModifyEbookUseCase(EbookRepository ebookRepository) {
        this.ebookRepository = ebookRepository;
    }

    private void execute(Ebook ebook){
        this.ebookRepository.modify(ebook);
    }


}
