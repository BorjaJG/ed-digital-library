package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResourceRepository;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UpdateLoanUseCase {

    private LoanRepository loanRepository;
    private DigitalResourceRepository digitalResourceRepository;
    private UserRepository userRepository;
    private LoanFactory loanFactory;

    public UpdateLoanUseCase(LoanRepository loanRepository, DigitalResourceRepository digitalResourceRepository, UserRepository userRepository, LoanFactory loanFactory) {
        this.loanRepository = loanRepository;
        this.digitalResourceRepository = digitalResourceRepository;
        this.userRepository = userRepository;
        this.loanFactory = loanFactory;
    }

    public void execute(String idLoan, String userId, String idDigitalResource, Date fechaI, Date fechaF, Date fechaE) {
        User user = userRepository.obtain(userId);
        DigitalResource digitalResource = digitalResourceRepository.obtainDigitalResource(idDigitalResource);
        Loan loan = loanFactory.modifyLoan(idLoan, digitalResource, user, fechaI, fechaF, fechaE);
        this.loanRepository.modify(loan);
    }

    public static Date generateCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}