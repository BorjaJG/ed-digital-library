package com.iesam.digitalibrary.user.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class NewUserUseCaseTest {

    @Mock
    UserRepository userRepository;
    NewUserUseCase newUserUseCase;
    @BeforeEach
    public void setUp(){
        newUserUseCase= new NewUserUseCase(userRepository);
    }
   @AfterEach
    public void clear(){
        newUserUseCase = null;
    }
    @Test
    public void GetUserAndSaveUser(){
        //Given
        User user = new User("1","2","juan","amigo@amigo","617929803","1","1",
                "1", "1","1","1","1","1","1");
        //When
        newUserUseCase.execute(user);
        //Then
        Mockito.verify(userRepository, Mockito.times(1)).save(user);

    }

}