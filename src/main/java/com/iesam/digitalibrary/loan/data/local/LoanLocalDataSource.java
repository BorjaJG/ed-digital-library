package com.iesam.digitalibrary.loan.data.local;


import com.iesam.digitalibrary.loan.domain.Loan;
import com.iesam.digitalibrary.user.domain.User;

import java.util.ArrayList;

public interface LoanLocalDataSource {

    // Save a loan locally
    void save(Loan loan);

    // Find all loans locally
    ArrayList<Loan> findAll();

    // Delete a loan by ID locally
    void delete(String idLoan);

    // Find a loan by ID locally
    Loan findById(String idLoan);

    // Modify a loan locally
    void modify(Loan loan);
}


