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
class ModifyUserUseCaseTest {
    @Mock
    UserRepository userRepository;
    @Mock
    UserFactory userFactory;

    ModifyUserUseCase modifyUserUseCase;

    @BeforeEach
    public void setUp() {
        modifyUserUseCase = new ModifyUserUseCase(userRepository, userFactory);
    }

    @AfterEach
    public void clear() {
        modifyUserUseCase = null;
    }

    @Test
    public void execute_ShouldModifyUser() {
        // Given
        String userId = "123";
        String name = "John";
        String email = "john123@biblio.com";
        String phoneNumber = "987654321";
        String address = "456 Elm St";
        String registrationDate = "2024-05-26";
        String userType = "Student";
        String status = "Active";
        String history = null;
        String fines = null;
        String transactions = null;
        String notificationPreference = null;
        String roleId = null;
        String additionalData = null;

        User user = new User(userId, name, email, phoneNumber, address, registrationDate, userType, status, history, fines, transactions, notificationPreference, roleId, additionalData);

        // When
        modifyUserUseCase.execute(userId, name, email, phoneNumber, address, registrationDate, userType, status, history, fines, transactions, notificationPreference, roleId, additionalData);

        // Then
        Mockito.verify(userRepository, times(1)).modify(user);
    }

    @Test
    public void generarCorreoElectronicoUser_ShouldGenerateCorrectEmailAddress() {
        // Given
        String nombre = "John";
        String userId = "123";

        // When
        String correo = ModifyUserUseCase.generarCorreoElectronicoUser(nombre, userId);

        // Then
        assertEquals("John123@biblio.com", correo);
    }
}