package com.iesam.digitalibrary.user.domain;

public class ModifyUserUseCase {
    // Declare a dependency on UserRepository
    public UserRepository userRepository;
    public  UserFactory userFactory;

    // Constructor to initialize UserRepository
    public ModifyUserUseCase(UserRepository userRepository,UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    // Method to execute modifying a user
    public void execute(String userId, String name, String email, String phoneNumber, String address, String registrationDate, String userType, String status, String history, String fines, String transactions, String notificationPreference, String roleId, String additionalData) {
        User user = userFactory.build(userId, name, email, phoneNumber, address, registrationDate, userType, status, history, fines, transactions, notificationPreference, roleId, additionalData);
        this.userRepository.modify(user);
    }
    public static String generarCorreoElectronicoUser(String nombre, String id) {
        // Format the email address: nombre + id + "@biblio.com"
        String correo = nombre + id + "@biblio.com";
        return correo;
    }
}
