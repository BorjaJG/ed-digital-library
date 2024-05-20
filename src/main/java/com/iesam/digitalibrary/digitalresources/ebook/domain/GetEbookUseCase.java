package com.iesam.digitalibrary.digitalresources.ebook.domain;

import com.iesam.digitalibrary.user.domain.User;

public class GetEbookUseCase {

    public EbookRepository ebookRepository;

    public GetEbookUseCase(EbookRepository ebookRepository) {
        this.ebookRepository = ebookRepository;
    }
    Ebook execte(String isbn){
        return this.ebookRepository.obtain(isbn);
    }
}
