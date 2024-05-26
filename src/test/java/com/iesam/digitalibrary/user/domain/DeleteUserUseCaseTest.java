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

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseTest {
    @Mock
    UserRepository userRepository;

    DeleteUserUseCase deleteUserUseCase;

    @BeforeEach
    public void setUp() {
        deleteUserUseCase = new DeleteUserUseCase(userRepository);
    }

    @AfterEach
    public void clear() {
        deleteUserUseCase = null;
    }

    @Test
    public void execute_ShouldDeleteUserById() {
        // Given
        String userId = "123";

        // When
        deleteUserUseCase.execute(userId);

        // Then
        Mockito.verify(userRepository, times(1)).delete(userId);
    }
}