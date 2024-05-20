package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.user.domain.User;

public class UpdateLoanUseCase {

    public LoanRepository loanRepository;

    public UpdateLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    private void  execute(Loan loan){
        this.loanRepository.modify(loan);
    }
}
