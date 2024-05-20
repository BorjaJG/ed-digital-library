package com.iesam.digitalibrary.loan.data.local;


import com.iesam.digitalibrary.loan.domain.Loan;
import com.iesam.digitalibrary.user.domain.User;

import java.util.ArrayList;

public interface LoanLocalDataSource {

    void save(Loan loan);

    ArrayList<Loan> findAll();

    void delete(String idLoan);

    Loan findById(String idLoan);

    void modify(Loan loan);
}


