package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.user.domain.User;

import java.util.ArrayList;

public interface LoanRepository {
    // Save a loan
    void save(Loan loan);

    // Delete a loan by ID
    void deleteLoan(String idLoan);

    // List all loans
    ArrayList<Loan> list();

    // Obtain a loan by ID
    Loan obtain(String idLoan);

    // Modify a loan
    void modify(Loan loan);
}
