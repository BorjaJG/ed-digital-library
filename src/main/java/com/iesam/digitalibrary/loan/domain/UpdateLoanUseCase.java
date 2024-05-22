package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.user.domain.User;

public class UpdateLoanUseCase {

    // Dependency on LoanRepository
    private LoanRepository loanRepository;

    // Constructor to initialize LoanRepository
    public UpdateLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    // Method to execute updating a loan
    void execute(Loan loan) {
        this.loanRepository.modify(loan); // Call modify method of LoanRepository to update the loan
    }

}