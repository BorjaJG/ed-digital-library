package com.iesam.digitalibrary.loan.domain;

public interface LoanRepository {
    void save(Loan loan);

    void deleteLoan(String idLoan);
}
