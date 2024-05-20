package com.iesam.digitalibrary.user.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseTest {

    @Mock
    UserRepository userRepository;

    GetUserUseCase getUserUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getUserUseCase = new GetUserUseCase(userRepository);
    }

    @AfterEach
    public void tearDown() {
        getUserUseCase = null;
    }

    @Test
    public void testGetUser() {
        // Given
        String userId = "1";

        // When
        getUserUseCase.execute(userId);

        // Then
        Mockito.verify(userRepository, Mockito.times(1)).obtain(userId);
    }

}