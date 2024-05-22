package com.iesam.digitalibrary.loan.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.iesam.digitalibrary.digitalresources.ebook.domain.GetEbookUseCase;
import com.iesam.digitalibrary.user.domain.GetUserUseCase;
import com.iesam.digitalibrary.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class GetLoanUseCaseTest {

    @Mock
    LoanRepository loanRepository;

    GetLoanUseCase getLoanUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getLoanUseCase = new GetLoanUseCase(loanRepository);
    }

    @AfterEach
    public void tearDown() {
        getLoanUseCase = null;
    }

    @Test
    public void testGetUser() {
        // Given
        String idLoan = "1";

        // When
        getLoanUseCase.execute(idLoan);

        // Then
        Mockito.verify(loanRepository, Mockito.times(1)).obtain(idLoan);
    }
}