package com.iesam.digitalibrary.digitalresources.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ListDigitalResourceUseCaseTest {

    @Mock
    private DigitalResourceRepository digitalResourceRepository;

    @InjectMocks
    private ListDigitalResourceUseCase listDigitalResourceUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testGetTypeFromId_Ebook() {
        // Arrange
        String id = "E123";

        // Act
        TypeDigitalResource type = ListDigitalResourceUseCase.getTypeFromId(id);

        // Assert
        assertEquals(TypeDigitalResource.EBOOK, type);
    }

    @Test
    public void testGetTypeFromId_Movie() {
        // Arrange
        String id = "M456";

        // Act
        TypeDigitalResource type = ListDigitalResourceUseCase.getTypeFromId(id);

        // Assert
        assertEquals(TypeDigitalResource.MOVIE, type);
    }

    @Test
    public void testGetTypeFromId_Unknown() {
        // Arrange
        String id = "X789";

        // Act
        TypeDigitalResource type = ListDigitalResourceUseCase.getTypeFromId(id);

        // Assert
        assertNull(type);
    }
}
