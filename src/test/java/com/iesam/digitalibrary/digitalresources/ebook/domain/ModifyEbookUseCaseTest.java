package com.iesam.digitalibrary.digitalresources.ebook.domain;

import com.iesam.digitalibrary.user.domain.ModifyUserUseCase;
import com.iesam.digitalibrary.user.domain.User;
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
class ModifyEbookUseCaseTest {
    @Mock
    EbookRepository ebookRepository;

    ModifyEbookUseCase modifyEbookUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        modifyEbookUseCase = new ModifyEbookUseCase(ebookRepository);
    }

    @AfterEach
    public void tearDown() {
        modifyEbookUseCase = null;
    }

    @Test
    public void testModifyEbook() {
        // Given
        Ebook ebook = new Ebook("1", "1", "1", "1", "1");

        // When
        modifyEbookUseCase.execute(ebook);

        // Then
        Mockito.verify(ebookRepository, Mockito.times(1)).modify(ebook);
    }
}