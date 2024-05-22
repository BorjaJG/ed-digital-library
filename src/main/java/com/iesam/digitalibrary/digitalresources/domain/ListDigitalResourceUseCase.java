package com.iesam.digitalibrary.digitalresources.domain;


import java.util.ArrayList;

public class ListDigitalResourceUseCase {
    // Repository to access digital resources
    private static DigitalResourceRepository digitalResourceRepository;

    // Constructor to initialize the repository
    public ListDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        ListDigitalResourceUseCase.digitalResourceRepository = digitalResourceRepository;
    }

    // Method to execute the use case of listing all digital resources
    public static ArrayList<DigitalResource> execute() {
        // Retrieve the list of digital resources from the repository
        return digitalResourceRepository.list();
    }
}
