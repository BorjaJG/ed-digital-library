package com.iesam.digitalibrary.digitalresources.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
 public class GetDigitalResourceUseCaseTest {

    private DigitalResourceRepository digitalResourceRepository;
    private GetDigitalResourceUseCase getDigitalResourceUseCase;

    @BeforeEach
    public void setUp() {
        digitalResourceRepository = mock(DigitalResourceRepository.class);
        getDigitalResourceUseCase = new GetDigitalResourceUseCase(digitalResourceRepository);
    }

    @Test
    public void testExecute() {
        // Arrange
        String resourceId = "1";
        DigitalResource expectedResource = new DigitalResource("1");
        when(digitalResourceRepository.obtainDigitalResource(resourceId)).thenReturn(expectedResource);

        // Act
        DigitalResource actualResource = getDigitalResourceUseCase.execute(resourceId);

        // Assert
        assertEquals(expectedResource, actualResource);
    }
}