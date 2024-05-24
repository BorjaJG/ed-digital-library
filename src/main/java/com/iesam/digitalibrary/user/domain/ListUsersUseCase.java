package com.iesam.digitalibrary.user.domain;

import java.util.ArrayList;
import java.util.List;

public class ListUsersUseCase {

    // Declare a dependency on UserRepository
    public UserRepository userRepository;

    // Constructor to initialize UserRepository
    public ListUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to execute listing all users
    public List<User> execute() {
        return  userRepository.list(); // Call list method of UserRepository to get all users
    }
}
