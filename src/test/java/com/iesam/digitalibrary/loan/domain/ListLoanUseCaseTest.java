package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.user.domain.ListUsersUseCase;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ListLoanUseCaseTest {
    @Mock
    LoanRepository loanRepository;

    ListLoanUseCase listLoanUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        listLoanUseCase = new ListLoanUseCase(loanRepository);
    }

    @AfterEach
    public void tearDown() {
        listLoanUseCase = null;
    }

    @Test
    public void givenLoansExist_whenListLoans_thenReturnCorrectList() {
        // Given
        User user1 = new User("1", "John Doe", "john.doe@example.com", "amigo@amigo", "617929803", "1", "1",
                "1", "1", "1", "1", "1", "1", "1");
        User user2 = new User("2", "Jane Doe", "jane.doe@example.com", "amigo@amigo", "617929803", "1", "1",
                "1", "1", "1", "1", "1", "1", "1");

        DigitalResource digitalResource1 = new DigitalResource("1");
        DigitalResource digitalResource2 = new DigitalResource("2");

        Date startDate1 = new Date(2024 - 1900, 0, 1); // Date is deprecated but used for simplicity
        Date endDate1 = new Date(2024 - 1900, 0, 10);
        Date startDate2 = new Date(2024 - 1900, 0, 11);
        Date endDate2 = new Date(2024 - 1900, 0, 20);

        Loan loan1 = new Loan("1",digitalResource1, user1, startDate1, endDate1, null);
        Loan loan2 = new Loan("2",digitalResource2, user2, startDate2, endDate2, null);
        ArrayList<Loan> loans = new ArrayList<>(Arrays.asList(loan1, loan2));
        Mockito.when(loanRepository.lits()).thenReturn(loans);

        // When
        ArrayList<Loan> result = loanRepository.lits();

        // Then
        Mockito.verify(loanRepository, Mockito.times(1)).lits();
        assertEquals(loans, result);
    }
    @Test
    public void givenNoLoansExist_whenListLoans_thenReturnEmptyList() {
        // Given
        Mockito.when(loanRepository.lits()).thenReturn(new ArrayList<>());

        // When
        List<Loan> result = loanRepository.lits();

        // Then
        Mockito.verify(loanRepository, Mockito.times(1)).lits();
        assertTrue(result.isEmpty());
    }
}