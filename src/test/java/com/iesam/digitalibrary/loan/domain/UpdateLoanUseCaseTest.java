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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private DigitalResourceRepository digitalResourceRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoanFactory loanFactory;

    @InjectMocks
    private UpdateLoanUseCase updateLoanUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        // Arrange
        String idLoan = "123";
        String userId = "456";
        String idDigitalResource = "789";
        Date fechaI = new Date();
        Date fechaF = new Date();
        Date fechaE = new Date();

        // Assuming User, DigitalResource, and Loan constructors accept necessary parameters
        User user = new User("John Doe", "john@example.com",null,null,
                null, null,null, null,null,
                null,null,null,null,null);
        DigitalResource digitalResource = new DigitalResource("E123");
        Loan loan = new Loan(idLoan, digitalResource, user, fechaI, fechaF);

        when(userRepository.obtain(userId)).thenReturn(user);
        when(digitalResourceRepository.obtainDigitalResource(idDigitalResource)).thenReturn(digitalResource);
        when(loanFactory.modifyLoan(idLoan, digitalResource, user, fechaI, fechaF, fechaE)).thenReturn(loan);

        // Act
        updateLoanUseCase.execute(idLoan, userId, idDigitalResource, fechaI, fechaF, fechaE);

        // Assert
        verify(userRepository, times(1)).obtain(userId);
        verify(digitalResourceRepository, times(1)).obtainDigitalResource(idDigitalResource);
        verify(loanFactory, times(1)).modifyLoan(idLoan, digitalResource, user, fechaI, fechaF, fechaE);
        verify(loanRepository, times(1)).modify(loan);
    }
}
