package com.iesam.digitalibrary.digitalresources.domain;

import com.iesam.digitalibrary.user.domain.User;

import java.util.ArrayList;

public class ListDigitalResourceUseCase {
    public DigitalResourceRepository digitalResourceRepository;

    public ListDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }
    private ArrayList<DigitalResource> execute(){
        return digitalResourceRepository.lits();
    }
}
