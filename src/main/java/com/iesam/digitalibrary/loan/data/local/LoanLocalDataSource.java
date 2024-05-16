package com.iesam.digitalibrary.loan.data.local;


import com.iesam.digitalibrary.loan.domain.Loan;

public interface LoanLocalDataSource {

    void save(Loan loan);


    void delete(String idLoan);
}
