package com.iesam.digitalibrary.user.domain;

public class ModifyUserUseCase {

    public UserRepository userRepository;


    public ModifyUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void  execute(User user){
        this.userRepository.modify(user);
    }
}
