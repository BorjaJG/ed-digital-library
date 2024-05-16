package com.iesam.digitalibrary.loan.domain;

public class DeleteLoanUseCase {

    public LoanRepository  loanRepository;

    public DeleteLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    private void execute(String idLoan){
        this.loanRepository.deleteLoan(idLoan);
    }


}
