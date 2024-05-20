package com.iesam.digitalibrary.user.domain;

public class DeleteUserUseCase {

    public UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    void execute(String userID){
         userRepository.delete(userID);

    }
}
