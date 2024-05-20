package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.user.domain.User;

import java.util.ArrayList;

public interface LoanRepository {
    void save(Loan loan);

    void deleteLoan(String idLoan);

    ArrayList<User> lits();

    Loan obtain(String idLoan);

    void modify(Loan loan);
}
