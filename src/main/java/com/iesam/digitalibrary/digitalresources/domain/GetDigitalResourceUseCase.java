package com.iesam.digitalibrary.digitalresources.domain;

public class GetDigitalResourceUseCase {
    // Repository to access digital resources
    private final DigitalResourceRepository digitalResourceRepository;

    // Constructor to initialize the repository
    public GetDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    // Method to execute the use case of obtaining a digital resource by ID
    public DigitalResource execute(String idDigitalResource) {
        // Retrieve the digital resource from the repository
        return this.digitalResourceRepository.obtainDR(idDigitalResource);
    }
}
