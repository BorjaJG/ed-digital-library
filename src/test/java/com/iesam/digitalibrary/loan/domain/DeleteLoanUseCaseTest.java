package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.user.domain.DeleteUserUseCase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class DeleteLoanUseCaseTest {
    @Mock
    LoanRepository  loanRepository;
    DeleteLoanUseCase deleteLoanUseCase;


    @BeforeEach
    public  void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteLoanUseCase = new DeleteLoanUseCase(loanRepository);
    }
    @AfterEach
    public void tearDown() {
        deleteLoanUseCase = null;
    }
    @Test
    public void testDeleteUser() {
        // Given
        String idLoan = "1";

        // When
        deleteLoanUseCase.execute(idLoan);

        // Then
        Mockito.verify(loanRepository, Mockito.times(1)).deleteLoan(idLoan);
    }

}