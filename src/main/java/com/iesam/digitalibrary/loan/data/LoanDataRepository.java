package com.iesam.digitalibrary.loan.data;

import com.iesam.digitalibrary.loan.data.local.LoanLocalDataSource;
import com.iesam.digitalibrary.loan.domain.Loan;


import java.util.ArrayList;


public class LoanDataRepository implements LoanLocalDataSource {
    // Dependency on LoanLocalDataSource
    private LoanLocalDataSource loanLocalDataSource;

    // Constructor to initialize with a LoanLocalDataSource
    public LoanDataRepository(LoanLocalDataSource loanLocalDataSource) {
        this.loanLocalDataSource = loanLocalDataSource;
    }

    // Save a loan using LoanLocalDataSource
    @Override
    public void save(Loan loan) {
        loanLocalDataSource.save(loan);
    }

    // Find all loans using LoanLocalDataSource
    @Override
    public ArrayList<Loan> findAll() {
        return loanLocalDataSource.findAll();
    }

    // Delete a loan by ID using LoanLocalDataSource
    @Override
    public void delete(String idLoan) {
        loanLocalDataSource.delete(idLoan);
    }

    // Find a loan by ID using LoanLocalDataSource
    @Override
    public Loan findById(String idLoan) {
        return loanLocalDataSource.findById(idLoan);
    }

    // Modify a loan using LoanLocalDataSource
    @Override
    public void modify(Loan loan) {
        loanLocalDataSource.modify(loan);
    }

}
