package com.iesam.digitalibrary.user.domain;

public class ModifyUserUseCase {
    // Declare a dependency on UserRepository
    public UserRepository userRepository;

    // Constructor to initialize UserRepository
    public ModifyUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to execute modifying a user
    void execute(User user) {
        this.userRepository.modify(user); // Call modify method of UserRepository to modify user
    }
}
