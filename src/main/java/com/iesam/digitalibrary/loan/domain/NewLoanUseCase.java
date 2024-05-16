package com.iesam.digitalibrary.loan.domain;

public class NewLoanUseCase{

public LoanRepository loanRepository;

    public NewLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    public void   execute(Loan loan){
        this.loanRepository.save(loan);
    }


}
