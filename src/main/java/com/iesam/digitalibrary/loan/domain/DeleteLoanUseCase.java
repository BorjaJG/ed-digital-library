package com.iesam.digitalibrary.loan.domain;

public class DeleteLoanUseCase {

    // Dependency on LoanRepository
    private LoanRepository loanRepository;

    // Constructor to initialize LoanRepository
    public DeleteLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    // Method to execute deleting a loan by ID
    public void execute(String idLoan){
        this.loanRepository.deleteLoan(idLoan); // Call deleteLoan method of LoanRepository
    }

}
