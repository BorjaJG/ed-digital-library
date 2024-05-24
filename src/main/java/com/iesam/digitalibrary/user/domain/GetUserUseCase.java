package com.iesam.digitalibrary.user.domain;

public class GetUserUseCase {

    // Declare a dependency on UserRepository
    public UserRepository userRepository;

    // Constructor to initialize UserRepository
    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to execute obtaining a user by ID
    public User execute(String userID) {
        return userRepository.obtain(userID); // Call obtain method of UserRepository to get user by ID
    }

}
