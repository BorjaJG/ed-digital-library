package com.iesam.digitalibrary.digitalresources.domain;

public class DeleteDigitalResourceUseCase {

    public final DigitalResourceRepository digitalResourceRepository;

    public DeleteDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public void execute(String idDigitalResource) {
        this.digitalResourceRepository.delete(idDigitalResource);
    }
}
