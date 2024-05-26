package com.iesam.digitalibrary.digitalresources.data;

import com.iesam.digitalibrary.digitalresources.data.local.DigitalResourceResourcesLocalDataSource;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DigitalResourceDataRepositoryTest {
    @Mock
    private DigitalResourceResourcesLocalDataSource digitalResourceResourcesLocalDataSource;

    @InjectMocks
    private DigitalResourceDataRepository digitalResourceDataRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtainDigitalResource() {
        // Arrange
        String resourceId = "1";
        DigitalResource expectedResource = new DigitalResource("1");
        when(digitalResourceResourcesLocalDataSource.findById(resourceId)).thenReturn(expectedResource);

        // Act
        DigitalResource actualResource = digitalResourceDataRepository.obtainDigitalResource(resourceId);

        // Assert
        assertEquals(expectedResource, actualResource);
    }

    @Test
    public void testList() {
        // Arrange
        List<DigitalResource> expectedResources = new ArrayList<>();
        expectedResources.add(new DigitalResource("1"));
        expectedResources.add(new DigitalResource("2"));
        when(digitalResourceResourcesLocalDataSource.findAll()).thenReturn(expectedResources);

        // Act
        List<DigitalResource> actualResources = digitalResourceDataRepository.list();

        // Assert
        assertEquals(expectedResources.size(), actualResources.size());
        assertEquals(expectedResources, actualResources);
    }

    @Test
    public void testDelete() {
        // Arrange
        String resourceId = "1";

        // Act
        digitalResourceDataRepository.delete(resourceId);

        // Assert
        verify(digitalResourceResourcesLocalDataSource, times(1)).delete(resourceId);
    }

    @Test
    public void testSave() {
        // Arrange
        DigitalResource resource = new DigitalResource("1");

        // Act
        digitalResourceDataRepository.save(resource);

        // Assert
        verify(digitalResourceResourcesLocalDataSource, times(1)).save(resource);
    }


}
