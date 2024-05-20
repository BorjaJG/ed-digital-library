package com.iesam.digitalibrary.user.domain;

public class GetUserUseCase {

    public UserRepository userRepository;

    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User execute(String userID){
        return this.userRepository.obtain(userID);
    }



}
