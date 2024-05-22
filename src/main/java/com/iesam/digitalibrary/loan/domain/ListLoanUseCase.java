package com.iesam.digitalibrary.loan.domain;

import java.util.ArrayList;

public class ListLoanUseCase {

    public LoanRepository loanRepository;


    public ListLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    private ArrayList<Loan> execute() {
        return loanRepository.lits();
    }
}
