package com.iesam.digitalibrary.digitalresources.domain;

import com.iesam.digitalibrary.user.domain.GetUserUseCase;
import com.iesam.digitalibrary.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GetDigitalResourceUseCaseTest {

    @Mock
    DigitalResourceRepository digitalResourceRepository;

    GetDigitalResourceUseCase getDigitalResourceUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getDigitalResourceUseCase = new GetDigitalResourceUseCase(digitalResourceRepository);
    }

    @AfterEach
    public void tearDown() {
        getDigitalResourceUseCase = null;
    }

    @Test
    public void testGetUser() {
        // Given
        String isbn = "1";

        // When
        getDigitalResourceUseCase.execute(isbn);

        // Then
        Mockito.verify(digitalResourceRepository, Mockito.times(1)).ObtainDR(isbn);
    }

}