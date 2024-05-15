package com.iesam.digitalibrary.digitalresources.ebook.domain;

public class NewEbook {
   private EbookRepository ebookRepository;

    public NewEbook(EbookRepository ebookRepository) {
        this.ebookRepository = ebookRepository;
    }

    private void  execute(Ebook ebook){
        this.ebookRepository.save(ebook);
    }


}
