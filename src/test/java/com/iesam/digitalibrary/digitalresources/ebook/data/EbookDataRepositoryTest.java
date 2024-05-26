package com.iesam.digitalibrary.digitalresources.ebook.data;

import static org.junit.jupiter.api.Assertions.*;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResourceRepository;
import com.iesam.digitalibrary.digitalresources.domain.NewDigitalResourceUseCase;
import com.iesam.digitalibrary.digitalresources.domain.TypeDigitalResource;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EbookDataRepositoryTest {

    @Mock
    private DigitalResourceRepository digitalResourceRepository;
    private TypeDigitalResource typeDigitalResource;

    @InjectMocks
    private NewDigitalResourceUseCase newDigitalResourceUseCase;


    public EbookDataRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        // Arrange
        DigitalResource digitalResource = new DigitalResource("1");

        // Act
        newDigitalResourceUseCase.execute(digitalResource);

        // Assert
        verify(digitalResourceRepository, times(1)).save(digitalResource);
    }

    @Test
    public void testGenerateUniqueIdDigitalResource_Ebook() {
        // Arrange
        int length = 8;

        // Act

        String id = NewDigitalResourceUseCase.generateUniqueIdDigitalResource(TypeDigitalResource.EBOOK, length);

        // Assert
        assertEquals(length, id.length());
        assertEquals('E', id.charAt(0));
    }

    @Test
    public void testGenerateUniqueIdDigitalResource_Movie() {
        // Arrange
        int length = 8;

        // Act

        String id = NewDigitalResourceUseCase.generateUniqueIdDigitalResource(TypeDigitalResource.MOVIE, length);

        // Assert
        assertEquals(length, id.length());
        assertEquals('M', id.charAt(0));
    }
}
