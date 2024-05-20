package com.iesam.digitalibrary.digitalresources.ebook.domain;

import com.iesam.digitalibrary.user.domain.NewUserUseCase;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class NewEbookUseCaseTest {

    @Mock
    EbookRepository ebookRepository;
    NewEbookUseCase newEbookUseCase;

    @BeforeEach
    void setUp() {
        newEbookUseCase = new NewEbookUseCase(ebookRepository);
    }

    @AfterEach
    void tearDown() {
        newEbookUseCase = null;
    }

    @Test
    public void GetEbookAndSaveUser() {
        //Given
        Ebook ebook =  new Ebook("1", "1", "1", "1", "1");


       //When
        newEbookUseCase.execute(ebook);
        //Then
        Mockito.verify(ebookRepository, Mockito.times(1)).save(ebook);

    }


}