package com.iesam.digitalibrary.digitalresources.domain;

import com.iesam.digitalibrary.digitalresources.ebook.domain.EbookFactory;
import com.iesam.digitalibrary.digitalresources.movie.domain.MovieFactory;

import java.util.Random;

public class NewDigitalResourceUseCase {
    private final DigitalResourceRepository digitalResourceRepository;

    public NewDigitalResourceUseCase(DigitalResourceRepository digitalResourceRepository) {
        this.digitalResourceRepository = digitalResourceRepository;
    }

    public void execute(DigitalResource digitalResource) {
        digitalResourceRepository.save(digitalResource);
    }


    // Method to generate a unique ID
    public static String generateUniqueIdDigitalResource(TypeDigitalResource type, int length) {
        String prefix = "";
        switch (type) {
            case EBOOK:
                prefix = "E";
                break;
            case MOVIE:
                prefix = "M";
                break;
        }

        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random RANDOM = new Random();
        StringBuilder sb = new StringBuilder(length);
        sb.append(prefix); // Agregar el prefijo correspondiente al tipo de recurso

        for (int i = 1; i < length; i++) { // Comenzar desde 1 para dejar espacio para el prefijo
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
