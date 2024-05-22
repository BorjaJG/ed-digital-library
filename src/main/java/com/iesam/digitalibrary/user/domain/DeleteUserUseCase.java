package com.iesam.digitalibrary.user.domain;

public class DeleteUserUseCase {

    // Declare a dependency on UserRepository
    public UserRepository userRepository;

    // Constructor to initialize UserRepository
    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to execute the deletion of a user by ID
    void execute(String userID) {
        userRepository.delete(userID); // Call delete method of UserRepository
    }
}
