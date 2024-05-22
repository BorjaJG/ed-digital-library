package com.iesam.digitalibrary.loan.domain;

public class NewLoanUseCase{

    // Dependency on LoanRepository
    private LoanRepository loanRepository;

    // Constructor to initialize LoanRepository
    public NewLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    // Method to execute saving a new loan
    public void execute(Loan loan){
        this.loanRepository.save(loan); // Call save method of LoanRepository to save the new loan
    }


}
