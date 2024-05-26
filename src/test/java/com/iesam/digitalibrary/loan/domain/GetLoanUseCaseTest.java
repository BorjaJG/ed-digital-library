package com.iesam.digitalibrary.loan.domain;


import com.iesam.digitalibrary.loan.domain.Loan;
import com.iesam.digitalibrary.loan.domain.LoanRepository;
import com.iesam.digitalibrary.loan.domain.GetLoanUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private GetLoanUseCase getLoanUseCase;

    @BeforeEach
    public void setUp() {
        // Initialization is handled by @ExtendWith(MockitoExtension.class) and @InjectMocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        // Arrange
        String idLoan = "123";
        Loan expectedLoan = new Loan(idLoan, null, null, null, null); // Adjust constructor parameters as necessary

        when(loanRepository.obtain(idLoan)).thenReturn(expectedLoan);

        // Act
        Loan actualLoan = getLoanUseCase.execute(idLoan);

        // Assert
        assertEquals(expectedLoan, actualLoan);
        verify(loanRepository, times(1)).obtain(idLoan);
    }
}
