package com.iesam.digitalibrary.loan.data.local;


import com.iesam.digitalibrary.loan.domain.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LoanMemLocalDataSource {
    // Data store to hold loans
    private Map<String, Loan> dataStore = new TreeMap<>();

    // Singleton instance
    private static LoanMemLocalDataSource instance = null;

    // Private constructor to enforce singleton pattern
    private LoanMemLocalDataSource() {
    }

    // Method to create a new instance of LoanMemLocalDataSource (singleton pattern)
    public static LoanMemLocalDataSource newInstance() {
        if (instance == null) {
            instance = new LoanMemLocalDataSource();
        }
        return instance;
    }

    // Save a loan to the data store
    public void save(Loan loan) {
        dataStore.put(loan.idLoan, loan);
    }

    // Save a list of loans to the data store
    public void saveList(List<Loan> loans) {
        for (Loan loan : loans) {
            save(loan);
        }
    }

    // Find a loan by ID
    public Loan findById(String idLoan) {
        return dataStore.get(idLoan);
    }

    // Find all loans in the data store
    public List<Loan> findAll() {
        return new ArrayList<>(dataStore.values());
    }

    // Delete a loan by ID
    public void delete(String idLoan) {
        dataStore.remove(idLoan);
    }
}
