package com.iesam.digitalibrary.digitalresources.domain;

public class GetDigitalResourceUseCase {
    public DigitalResourceRepository digitalResourceRepository;

    public GetDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }
    void execute(String idDigitalResource){
        this.digitalResourceRepository.ObtainDR(idDigitalResource);
    }
}
