package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.user.domain.User;

public class GetLoanUseCase {

    public LoanRepository loanRepository;

    public GetLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    Loan execute(String idLoan){
        return this.loanRepository.obtain(idLoan);
    }




}
