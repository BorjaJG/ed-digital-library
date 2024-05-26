package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.user.domain.User;

public class GetLoanUseCase {

    // Dependency on LoanRepository
    private LoanRepository loanRepository;

    // Constructor to initialize LoanRepository
    public GetLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    // Method to execute obtaining a loan by ID
    public Loan execute(String idLoan) {
        return this.loanRepository.obtain(idLoan); // Call obtain method of LoanRepository
    }

}
