package com.iesam.digitalibrary.loan.data;

import com.iesam.digitalibrary.loan.data.local.LoanLocalDataSource;
import com.iesam.digitalibrary.loan.domain.Loan;
import com.iesam.digitalibrary.user.domain.User;

import java.util.ArrayList;


public class LoanDataRepository implements LoanLocalDataSource {
    LoanLocalDataSource loanLocalDataSource;

    public LoanDataRepository(LoanLocalDataSource loanFileLocalDataSource) {
        this.loanLocalDataSource = loanFileLocalDataSource;
    }

    @Override
    public void save(Loan loan) {
        loanLocalDataSource.save(loan);
    }

    @Override
    public ArrayList<Loan> findAll() {
        return loanLocalDataSource.findAll();
    }

    @Override
    public void delete(String idLoan) {

        loanLocalDataSource.delete(idLoan);

    }


}
