package com.iesam.digitalibrary.loan.domain;


import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.user.domain.User;

import java.util.Date;

public class Loan {

    // Attributes
    public final String idLoan;
    public final DigitalResource digitalResource;
    public final User user;
    public final Date fechaI; // Loan start date
    public final Date fechaF; // Loan end date
    public final Date fechaE; // Expected return date

    // Constructor
    public Loan(String idLoan, DigitalResource digitalResource, User user, Date fechaI, Date fechaF) {
        this.idLoan = idLoan;
        this.digitalResource = digitalResource;
        this.user = user;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
        this.fechaE=null;


    }

    public Loan(String idLoan, DigitalResource digitalResource, User user, Date fechaI, Date fechaF, Date fechaE) {
        this.idLoan = idLoan;
        this.digitalResource = digitalResource;
        this.user = user;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
        this.fechaE = fechaE;
    }

    // toString method to represent object as a String
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
