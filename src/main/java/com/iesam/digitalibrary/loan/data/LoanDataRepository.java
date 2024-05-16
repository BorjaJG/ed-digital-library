package com.iesam.digitalibrary.loan.data;

import com.iesam.digitalibrary.loan.data.local.LoanLocalDataSource;
import com.iesam.digitalibrary.loan.domain.Loan;


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
    public void delete(String idLoan) {

    }


}
