package com.iesam.digitalibrary.digitalresources.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class DeleteDigitalResourceUseCaseTest {

    private DigitalResourceRepository digitalResourceRepository;
    private DeleteDigitalResourceUseCase deleteDigitalResourceUseCase;

    @BeforeEach
    public void setUp() {
        digitalResourceRepository = mock(DigitalResourceRepository.class);
        deleteDigitalResourceUseCase = new DeleteDigitalResourceUseCase(digitalResourceRepository);
    }

    @Test
    public void testExecute() {
        // Arrange
        String resourceId = "1";

        // Act
        deleteDigitalResourceUseCase.execute(resourceId);

        // Assert
        verify(digitalResourceRepository, times(1)).delete(resourceId);
    }
}
