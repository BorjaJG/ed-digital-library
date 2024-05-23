package com.iesam.digitalibrary.digitalresources.domain;

import com.iesam.digitalibrary.user.domain.ListUsersUseCase;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ListDigitalResourceUseCaseTest {


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
    public void givenDGsExist_whenListDR_thenReturnCorrectList() {
        DigitalResource digitalResource1 = new DigitalResource("1");
        DigitalResource digitalResource2 = new DigitalResource("2");
        // Given
        ArrayList<DigitalResource> digitalResources = new ArrayList<>(Arrays.asList(digitalResource1, digitalResource2));
        Mockito.when(digitalResourceRepository.list()).thenReturn(digitalResources);

        // When
        ArrayList<DigitalResource> result = digitalResourceRepository.list();

        // Then
        Mockito.verify(digitalResourceRepository, Mockito.times(1)).list();
        assertEquals(digitalResources, result);
    }
    @Test
    public void givenNoDGsExist_whenListDR_thenReturnEmptyList() {
        // Given
        Mockito.when(digitalResourceRepository.list()).thenReturn(new ArrayList<>());

        // When
        ArrayList<DigitalResource> result = ListDigitalResourceUseCase.execute();

        // Then
        Mockito.verify(digitalResourceRepository, Mockito.times(1)).list();
        assertTrue(result.isEmpty());
    }


}