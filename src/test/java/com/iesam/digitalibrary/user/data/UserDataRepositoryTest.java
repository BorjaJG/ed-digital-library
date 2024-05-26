package com.iesam.digitalibrary.user.data;

import com.iesam.digitalibrary.user.data.local.UserLocalDataSource;
import com.iesam.digitalibrary.user.domain.User;
import org.junit.jupiter.api.AfterEach;
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
class UserDataRepositoryTest {

    @Mock
    private UserLocalDataSource userLocalDataSource;

    @InjectMocks
    private UserDataRepository userDataRepository;

    @Test
    public void testSave() {
        // Arrange
        User user = new User("1", "John", "john@example.com", "1234567890",
                null, null,null,null,null,
                null, null, null, null,null);

        // Act
        userDataRepository.save(user);

        // Assert
        verify(userLocalDataSource, times(1)).save(user);
    }

    @Test
    public void testDelete() {
        // Arrange
        String userId = "1";

        // Act
        userDataRepository.delete(userId);

        // Assert
        verify(userLocalDataSource, times(1)).delete(userId);
    }

    @Test
    public void testObtain() {
        // Arrange
        String userId = "1";
        User expectedUser = new User("1", "John", "john@example.com", "1234567890",
                null, null,null,null,null,
                null, null, null, null,null);
        when(userLocalDataSource.findById(userId)).thenReturn(expectedUser);

        // Act
        User actualUser = userDataRepository.obtain(userId);

        // Assert
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testModify() {
        // Arrange
        User user = new User("1", "John", "john@example.com", "1234567890",
                null, null,null,null,null,
                null, null, null, null,null);

        // Act
        userDataRepository.modify(user);

        // Assert
        verify(userLocalDataSource, times(1)).modify(user);
    }

    @Test
    public void testList() {
        // Arrange
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User("1", "2", "john@example.com", "2",
                null, null,null,null,null,
                null, null, null, null,null));
        expectedUsers.add(new User("2", "3", "john@example.com", "4",
                null, null,null,null,null,
                null, null, null, null,null));
        when(userLocalDataSource.findAll()).thenReturn(expectedUsers);

        // Act
        List<User> actualUsers = userDataRepository.list();

        // Assert
        assertEquals(expectedUsers.size(), actualUsers.size());
        for (int i = 0; i < expectedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i), actualUsers.get(i));
        }
    }
}