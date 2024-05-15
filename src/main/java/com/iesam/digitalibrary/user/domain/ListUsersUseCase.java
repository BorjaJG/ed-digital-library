package com.iesam.digitalibrary.user.domain;

import java.util.ArrayList;

public class ListUsersUseCase {


    public UserRepository userRepository;

    public ListUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private ArrayList<User>  execute(){
        return userRepository.lits();
    }



}