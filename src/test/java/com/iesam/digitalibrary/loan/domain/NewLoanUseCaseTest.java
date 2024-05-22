package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.user.domain.NewUserUseCase;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class NewLoanUseCaseTest {

    @Mock
    LoanRepository loanRepository;
    NewLoanUseCase newLoanUseCase;

    @BeforeEach
    public void setUp() {
        newLoanUseCase = new NewLoanUseCase(loanRepository);
    }

    @AfterEach
    public void clear() {
        loanRepository = null;
    }

    @Test
    public void GetUserAndSaveUser() {

        User user1 = new User("1", "John Doe", "john.doe@example.com", "amigo@amigo", "617929803", "1", "1",
                "1", "1", "1", "1", "1", "1", "1");
        DigitalResource digitalResource1 = new DigitalResource("1");
        Date startDate1 = new Date(2024 - 1900, 0, 1); // Date is deprecated but used for simplicity
        Date endDate1 = new Date(2024 - 1900, 0, 10);
        //Given
        Loan loan = new Loan("1",digitalResource1, user1, startDate1, endDate1, null);
        //When
        newLoanUseCase.execute(loan);
        //Then
        Mockito.verify(loanRepository, Mockito.times(1)).save(loan);

    }

}