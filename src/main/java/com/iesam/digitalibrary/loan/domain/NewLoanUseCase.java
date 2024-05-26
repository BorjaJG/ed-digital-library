package com.iesam.digitalibrary.loan.domain;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResourceRepository;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;


public  class NewLoanUseCase {
    private LoanRepository loanRepository;
    private DigitalResourceRepository digitalResourceRepository;
    private UserRepository userRepository;
    private LoanFactory loanFactory;

    public NewLoanUseCase(LoanRepository loanRepository, DigitalResourceRepository digitalResourceRepository, UserRepository userRepository, LoanFactory loanFactory) {
        this.loanRepository = loanRepository;
        this.digitalResourceRepository = digitalResourceRepository;
        this.userRepository = userRepository;
        this.loanFactory = loanFactory;
    }

    public void execute(String idLoan, String userId, String idDigitalResource, Date fechaI, Date fechaF) {
        User user = userRepository.obtain(userId);
        DigitalResource digitalResource = digitalResourceRepository.obtainDigitalResource(idDigitalResource);
        Loan loan = loanFactory.newLoan(idLoan, digitalResource, user, fechaI, fechaF);
        this.loanRepository.save(loan);
    }

    // Métodos estáticos para generar ID único y fechas
    public static String generateUniqueIdLoan(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random RANDOM = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static Date generateCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date generateDateFiveDaysAhead() {
        LocalDate futureDate = LocalDate.now().plusDays(5);
        return Date.from(futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
