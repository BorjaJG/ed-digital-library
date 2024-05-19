package com.iesam.digitalibrary.loan.domain;


import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.user.domain.User;

import java.util.Date;

public class Loan {


    public final String idLoan;
    public final DigitalResource digitalResource;
    public final User user;
    public final Date fechaI;
    public final Date fechaF;
    public final Date fechaE;

    public Loan(String idLoan, DigitalResource digitalResource, User user, Date fechaI, Date fechaF, Date fechaE) {
        this.idLoan = idLoan;
        this.digitalResource = digitalResource;
        this.user = user;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
        this.fechaE = fechaE;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "idLoan='" + idLoan + '\'' +
                ", digitalResource=" + digitalResource +
                ", user=" + user +
                ", fechaI='" + fechaI + '\'' +
                ", fechaF='" + fechaF + '\'' +
                ", fechaE='" + fechaE + '\'' +
                '}';
    }
}
