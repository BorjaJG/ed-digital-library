package com.iesam.digitalibrary.user.domain;


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
class ListUsersUseCaseTest {

    @Mock
    UserRepository userRepository;

    ListUsersUseCase listUsersUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        listUsersUseCase = new ListUsersUseCase(userRepository);
    }

    @AfterEach
    public void tearDown() {
        listUsersUseCase = null;
    }

    @Test
    public void givenUsersExist_whenListUsers_thenReturnCorrectList() {
        // Given
        User user1 = new User("1", "John Doe", "john.doe@example.com", "amigo@amigo", "617929803", "1", "1",
                "1", "1", "1", "1", "1", "1", "1");
        User user2 = new User("2", "Jane Doe", "jane.doe@example.com", "amigo@amigo", "617929803", "1", "1",
                "1", "1", "1", "1", "1", "1", "1");
        ArrayList<User> users = new ArrayList<>(Arrays.asList(user1, user2));
        Mockito.when(userRepository.lits()).thenReturn(users);

        // When
        ArrayList<User> result = listUsersUseCase.execute();

        // Then
        Mockito.verify(userRepository, Mockito.times(1)).lits();
        assertEquals(users, result);
    }
    @Test
    public void givenNoUsersExist_whenListUsers_thenReturnEmptyList() {
        // Given
        Mockito.when(userRepository.lits()).thenReturn(new ArrayList<>());

        // When
        List<User> result = listUsersUseCase.execute();

        // Then
        Mockito.verify(userRepository, Mockito.times(1)).lits();
        assertTrue(result.isEmpty());
    }
}