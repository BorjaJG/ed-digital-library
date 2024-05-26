package com.iesam.digitalibrary.user.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListUsersUseCaseTest {
    @Mock
    UserRepository userRepository;

    ListUsersUseCase listUsersUseCase;

    @BeforeEach
    public void setUp() {
        listUsersUseCase = new ListUsersUseCase(userRepository);
    }

    @AfterEach
    public void clear() {
        listUsersUseCase = null;
    }

    @Test
    public void execute_ShouldReturnListOfUsers() {
        // Given
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User("1", "John", "john@biblio.com", "123456789", "123 Elm St", null, null, null, null, null, null, null, null, null));
        expectedUsers.add(new User("2", "Jane", "jane@biblio.com", "987654321", "456 Oak St", null, null, null, null, null, null, null, null, null));

        // Stub the behavior of userRepository.list() to return the expected users
        when(userRepository.list()).thenReturn(expectedUsers);

        // When
        List<User> actualUsers = listUsersUseCase.execute();

        // Then
        assertEquals(expectedUsers.size(), actualUsers.size());
        assertEquals(expectedUsers, actualUsers);
        Mockito.verify(userRepository, times(1)).list();
    }
}