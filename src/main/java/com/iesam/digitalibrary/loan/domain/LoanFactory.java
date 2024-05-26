package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.user.domain.User;

import java.util.Date;

public class LoanFactory {

    public Loan modifyLoan(String idLoan, DigitalResource digitalResource, User user,Date fechaI, Date fechaF, Date fechaE) {
        return new Loan(idLoan, digitalResource, user, fechaI,fechaF, fechaE );
    }

    public Loan newLoan(String idLoan, DigitalResource digitalResource, User user,Date fechaI, Date fechaF) {

        return new Loan(idLoan, digitalResource, user, fechaI, fechaF );
    }
}
