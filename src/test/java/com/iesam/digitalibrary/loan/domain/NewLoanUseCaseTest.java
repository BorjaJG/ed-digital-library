package com.iesam.digitalibrary.loan.domain;



import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResourceRepository;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NewLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private DigitalResourceRepository digitalResourceRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoanFactory loanFactory;

    @InjectMocks
    private NewLoanUseCase newLoanUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        // Arrange
        String idLoan = "L123";
        String userId = "U123";
        String idDigitalResource = "D123";
        Date fechaI = new Date();
        Date fechaF = new Date(fechaI.getTime() + (5 * 24 * 60 * 60 * 1000L)); // 5 days ahead

        User user = new User(userId, "John Doe", null,null,null,null,
                null,null,null,null,null,null,
                null,null);
        DigitalResource digitalResource = new DigitalResource(idDigitalResource);
        Loan loan = new Loan(idLoan, digitalResource, user, fechaI, fechaF);

        when(userRepository.obtain(userId)).thenReturn(user);
        when(digitalResourceRepository.obtainDigitalResource(idDigitalResource)).thenReturn(digitalResource);
        when(loanFactory.newLoan(idLoan, digitalResource, user, fechaI, fechaF)).thenReturn(loan);

        // Act
        newLoanUseCase.execute(idLoan, userId, idDigitalResource, fechaI, fechaF);

        // Assert
        verify(userRepository, times(1)).obtain(userId);
        verify(digitalResourceRepository, times(1)).obtainDigitalResource(idDigitalResource);
        verify(loanFactory, times(1)).newLoan(idLoan, digitalResource, user, fechaI, fechaF);
        verify(loanRepository, times(1)).save(loan);
    }
}
