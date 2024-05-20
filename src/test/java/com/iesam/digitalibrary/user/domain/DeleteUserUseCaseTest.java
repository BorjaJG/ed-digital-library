package com.iesam.digitalibrary.user.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseTest {
    @Mock
    UserRepository userRepository;

    DeleteUserUseCase deleteUserUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteUserUseCase = new DeleteUserUseCase(userRepository);
    }

    @AfterEach
    public void tearDown() {
        deleteUserUseCase = null;
    }

    @Test
    public void testDeleteUser() {
        // Given
        String userId = "1";

        // When
        deleteUserUseCase.execute(userId);

        // Then
        Mockito.verify(userRepository, Mockito.times(1)).delete(userId);
    }






}