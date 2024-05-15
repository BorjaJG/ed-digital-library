package com.iesam.digitalibrary.digitalresources.ebook.domain;

public class NewEbookUseCase {
   private EbookRepository ebookRepository;

    public NewEbookUseCase(EbookRepository ebookRepository) {
        this.ebookRepository = ebookRepository;
    }

    private void  execute(Ebook ebook){
        this.ebookRepository.save(ebook);
    }


}
