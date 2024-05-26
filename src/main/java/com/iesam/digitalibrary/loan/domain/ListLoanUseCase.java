package com.iesam.digitalibrary.loan.domain;

import java.util.ArrayList;

public class ListLoanUseCase {
    // Dependency on LoanRepository
    public LoanRepository loanRepository;

    // Constructor to initialize LoanRepository
    public ListLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    // Method to execute listing all loans
    public ArrayList<Loan> execute() {
        return loanRepository.list(); // Call list method of LoanRepository to get all loans
    }
}