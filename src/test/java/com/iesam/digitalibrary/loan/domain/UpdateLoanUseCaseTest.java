package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.user.domain.ModifyUserUseCase;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UpdateLoanUseCaseTest {
    @Mock
    LoanRepository loanRepository;

    UpdateLoanUseCase updateLoanUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        updateLoanUseCase = new UpdateLoanUseCase(loanRepository);
    }

    @AfterEach
    public void tearDown() {
        updateLoanUseCase = null;
    }

    @Test
    public void testModifyUser() {
        // Given
        User user1 = new User("1", "John Doe", "john.doe@example.com", "amigo@amigo", "617929803", "1", "1",
                "1", "1", "1", "1", "1", "1", "1");
        DigitalResource digitalResource1 = new DigitalResource("1");
        Date startDate1 = new Date(2024 - 1900, 0, 1); // Date is deprecated but used for simplicity
        Date endDate1 = new Date(2024 - 1900, 0, 10);
        //Given
        Loan loan = new Loan("1",digitalResource1, user1, startDate1, endDate1, null);
        // When
        updateLoanUseCase.execute(loan);

        // Then
        Mockito.verify(loanRepository, Mockito.times(1)).modify(loan);
    }
}