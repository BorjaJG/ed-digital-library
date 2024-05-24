package com.iesam.digitalibrary.user.domain;

import java.util.Random;

public class NewUserUseCase {
    // Declare a dependency UserRepository
    private final UserRepository userRepository;

    // Constructor to initialize UserRepository
    public NewUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // Method to execute saving a new user
    public void execute(User user) {
        // Save user
         userRepository.save(user);
    }
    // Method to generate a unique ID
    public static String generateUniqueIdUser(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXY0123456789";
        Random RANDOM = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    public static String generarCorreoElectronicoUser(String nombre, String id) {
        // Format the email address: nombre + id + "@biblio.com"
        String correo = nombre + id + "@biblio.com";
        return correo;
    }
}
