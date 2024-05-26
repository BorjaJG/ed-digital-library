package com.iesam.digitalibrary.digitalresources.domain;


import java.util.ArrayList;
import java.util.List;

public class ListDigitalResourceUseCase {
    // Repository to access digital resources
    private static DigitalResourceRepository digitalResourceRepository;

    // Constructor to initialize the repository
    public ListDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        ListDigitalResourceUseCase.digitalResourceRepository = digitalResourceRepository;
    }

    // Method to execute the use case of listing all digital resources
    public static List<DigitalResource> execute() {
        // Retrieve the list of digital resources from the repository
        return digitalResourceRepository.list();
    }
    public static TypeDigitalResource getTypeFromId(String id) {


        char prefix = id.charAt(0);

        switch (prefix) {
            case 'E':
                return TypeDigitalResource.EBOOK;
            case 'M':
                return TypeDigitalResource.MOVIE;
            default:
                return null;// Devolver tipo desconocido si el prefijo es inválido
        }
    }


}
