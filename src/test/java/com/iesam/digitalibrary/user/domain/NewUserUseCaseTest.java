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
class NewUserUseCaseTest {
    @Mock
    UserRepository userRepository;

    NewUserUseCase newUserUseCase;

    @BeforeEach
    public void setUp() {
        newUserUseCase = new NewUserUseCase(userRepository);
    }

    @AfterEach
    public void clear() {
        newUserUseCase = null;
    }

    @Test
    public void execute_ShouldSaveNewUser() {
        // Given
        String userId = NewUserUseCase.generateUniqueIdUser(8); // Generate unique user ID
        String email = NewUserUseCase.generarCorreoElectronicoUser("John", userId); // Generate email
        User user = new User(userId, "John", email, "123456789", "123 Main St", null, null, null, null, null, null, null, null, null);

        // When
        newUserUseCase.execute(user);

        // Then
        Mockito.verify(userRepository, times(1)).save(user);
    }

    @Test
    public void generateUniqueIdUser_ShouldReturnUniqueIdWithSpecifiedLength() {
        // Given
        int length = 10;

        // When
        String uniqueId = NewUserUseCase.generateUniqueIdUser(length);

        // Then
        assertEquals(length, uniqueId.length());
    }

    @Test
    public void generarCorreoElectronicoUser_ShouldGenerateCorrectEmailAddress() {
        // Given
        String nombre = "John";
        String userId = "ABCDEFGHIJ";

        // When
        String correo = NewUserUseCase.generarCorreoElectronicoUser(nombre, userId);

        // Then
        assertEquals("JohnABCDEFGHIJ@biblio.com", correo);
    }
}