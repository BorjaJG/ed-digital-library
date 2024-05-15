package com.iesam.digitalibrary.digitalresources.domain;

public class GetDigitalResourceUseCase {
    public DigitalResourceRepository digitalResourceRepository;

    public GetDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }
    private void execute(String idDigitalResource){
        this.digitalResourceRepository.listDR(idDigitalResource);
    }
}
