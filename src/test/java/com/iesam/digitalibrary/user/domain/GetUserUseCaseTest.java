package com.iesam.digitalibrary.user.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseTest {
    @Mock
    UserRepository userRepository;

    GetUserUseCase getUserUseCase;

    @BeforeEach
    public void setUp() {
        getUserUseCase = new GetUserUseCase(userRepository);
    }

    @AfterEach
    public void clear() {
        getUserUseCase = null;
    }

    @Test
    public void execute_ShouldReturnUserById() {
        // Given
        String userId = "123";
        User expectedUser = new User(userId, "John", "john@biblio.com", "123456789", "123 Elm St", null, null, null, null, null, null, null, null, null);

        // Stub the behavior of userRepository.obtain() to return the expected user
        when(userRepository.obtain(userId)).thenReturn(expectedUser);

        // When
        User actualUser = getUserUseCase.execute(userId);

        // Then
        assertEquals(expectedUser, actualUser);
        Mockito.verify(userRepository, times(1)).obtain(userId);
    }
}