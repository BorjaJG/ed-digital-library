package com.iesam.digitalibrary.digitalresources.ebook.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.iesam.digitalibrary.user.domain.GetUserUseCase;
import com.iesam.digitalibrary.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class GetEbookUseCaseTest {


    @Mock
    EbookRepository ebookRepository;

    GetEbookUseCase getEbookUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getEbookUseCase = new GetEbookUseCase(ebookRepository);
    }

    @AfterEach
    public void tearDown() {
        getEbookUseCase = null;
    }

    @Test
    public void testGetUser() {
        // Given
        String isbn = "1";

        // When
        getEbookUseCase.execute(isbn);

        // Then
        Mockito.verify(ebookRepository, Mockito.times(1)).obtain(isbn);
    }




}