package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.user.domain.User;

import java.util.ArrayList;

public class ListLoanUseCase {

    public LoanRepository loanRepository;


    public ListLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    private ArrayList<User> execute() {
        return loanRepository.lits();
    }
}
