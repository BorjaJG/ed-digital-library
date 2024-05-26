package com.iesam.digitalibrary.loan.data;

import com.iesam.digitalibrary.loan.data.local.LoanLocalDataSource;
import com.iesam.digitalibrary.loan.domain.Loan;
import com.iesam.digitalibrary.loan.domain.LoanRepository;


import java.util.ArrayList;


public class LoanDataRepository implements LoanRepository {
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

    @Override
    public void deleteLoan(String idLoan) {
       loanLocalDataSource.delete(idLoan);
    }

    @Override
    public ArrayList<Loan> list() {
        return loanLocalDataSource.findAll();
    }

    @Override
    public Loan obtain(String idLoan) {
        return loanLocalDataSource.findById(idLoan);
    }

    // Modify a loan using LoanLocalDataSource
    @Override
    public void modify(Loan loan) {
        loanLocalDataSource.modify(loan);
    }

}
