package com.iesam.digitalibrary.digitalresources.ebook.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.iesam.digitalibrary.loan.domain.LoanRepository;
import com.iesam.digitalibrary.user.domain.DeleteUserUseCase;
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
class DeleteEbookUseCaseTest {

    @Mock
    EbookRepository ebookRepository;

    DeleteEbookUseCase deleteEbookUseCase;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteEbookUseCase = new DeleteEbookUseCase(ebookRepository);
    }

    @AfterEach
    public void tearDown() {
        deleteEbookUseCase = null;
    }


    @Test
    public void testDeleteUser() {
        // Given
        String isbn = "1";

        // When
        deleteEbookUseCase.execute(isbn);

        // Then
        Mockito.verify(ebookRepository, Mockito.times(1)).delete(isbn);
    }




}