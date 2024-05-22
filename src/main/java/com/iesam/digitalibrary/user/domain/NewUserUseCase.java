package com.iesam.digitalibrary.user.domain;

public class NewUserUseCase {
    // Declare a dependency on UserRepository
    private UserRepository userRepository;

    // Constructor to initialize UserRepository
    public NewUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to execute saving a new user
    public void execute(User user){
        this.userRepository.save(user); // Call save method of UserRepository to save the new user
    }
}
