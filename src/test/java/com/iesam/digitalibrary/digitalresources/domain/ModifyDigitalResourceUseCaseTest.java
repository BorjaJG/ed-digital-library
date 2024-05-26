package com.iesam.digitalibrary.digitalresources.domain;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ModifyDigitalResourceUseCaseTest {

    @Mock
    private DigitalResourceRepository digitalResourceRepository;

    @InjectMocks
    private ModifyDigitalResourceUseCase modifyDigitalResourceUseCase;

    public ModifyDigitalResourceUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        // Arrange
        DigitalResource digitalResource = new DigitalResource("1");

        // Act
        modifyDigitalResourceUseCase.execute(digitalResource);

        // Assert
        verify(digitalResourceRepository, times(1)).modify(digitalResource);
    }

    @Test
    public void testGetTypeFromIdEbook() {
        // Arrange
        String id = "E123";

        // Act
        TypeDigitalResource type = ModifyDigitalResourceUseCase.getTypeFromId(id);

        // Assert
        assertEquals(TypeDigitalResource.EBOOK, type);
    }

    @Test
    public void testGetTypeFromIdMovie() {
        // Arrange
        String id = "M456";

        // Act
        TypeDigitalResource type = ModifyDigitalResourceUseCase.getTypeFromId(id);

        // Assert
        assertEquals(TypeDigitalResource.MOVIE, type);
    }

    @Test
    public void testGetTypeFromIdUnknown() {
        // Arrange
        String id = "X789";

        // Act
        TypeDigitalResource type = ModifyDigitalResourceUseCase.getTypeFromId(id);

        // Assert
        assertNull(type);
    }

    @Test
    public void testChangeTypeOfIdEbook() {
        // Arrange
        String id = "M123";

        // Act
        String newId = ModifyDigitalResourceUseCase.changeTypeOfId(id, TypeDigitalResource.EBOOK);

        // Assert
        assertEquals("E123", newId);
    }

    @Test
    public void testChangeTypeOfIdMovie() {
        // Arrange
        String id = "E456";

        // Act
        String newId = ModifyDigitalResourceUseCase.changeTypeOfId(id, TypeDigitalResource.MOVIE);

        // Assert
        assertEquals("M456", newId);
    }

    @Test
    public void testChangeTypeOfIdInvalidID() {
        // Arrange
        String id = null;

        // Act
        String newId = ModifyDigitalResourceUseCase.changeTypeOfId(id, TypeDigitalResource.MOVIE);

        // Assert
        assertEquals("InvalidID", newId);
    }

    @Test
    public void testChangeTypeOfIdInvalidType() {
        // Arrange
        String id = "E123";

        // Act
        String newId = ModifyDigitalResourceUseCase.changeTypeOfId(id, TypeDigitalResource.AUDIO); // Tipo inválido

        // Assert
        assertEquals("InvalidType", newId);
    }
}