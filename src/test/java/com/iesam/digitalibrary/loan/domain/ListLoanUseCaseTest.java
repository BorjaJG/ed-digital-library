package com.iesam.digitalibrary.loan.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private ListLoanUseCase listLoanUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        // Arrange
        List<Loan> expectedLoans = new ArrayList<>();
        String idLoan = "123";
        Loan expectedLoan = new Loan(idLoan, null, null, null, null);
        String idLoan3 = "323";
        Loan expectedLoan3 = new Loan(idLoan3, null, null, null, null);
        when(loanRepository.list()).thenReturn((ArrayList<Loan>) expectedLoans);

        // Act
        List<Loan> actualLoans = listLoanUseCase.execute();

        // Assert
        assertEquals(expectedLoans, actualLoans);
    }
}