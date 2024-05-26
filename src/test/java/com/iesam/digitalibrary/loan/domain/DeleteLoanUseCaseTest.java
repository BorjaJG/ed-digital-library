package com.iesam.digitalibrary.loan.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private DeleteLoanUseCase deleteLoanUseCase;

    @BeforeEach
    public void setUp() {
        // Initialization is handled by @ExtendWith(MockitoExtension.class) and @InjectMocks
    }

    @Test
    public void testExecute() {
        // Arrange
        String loanId = "1";

        // Act
        deleteLoanUseCase.execute(loanId);

        // Assert
        verify(loanRepository, times(1)).deleteLoan(loanId);
    }
}