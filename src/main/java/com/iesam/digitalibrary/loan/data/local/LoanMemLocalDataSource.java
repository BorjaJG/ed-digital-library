package com.iesam.digitalibrary.loan.data.local;


import com.iesam.digitalibrary.loan.domain.Loan;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LoanMemLocalDataSource {

    private Map<String, Loan> dataStore = new TreeMap<>();
    private static LoanMemLocalDataSource instance=null;
    public LoanMemLocalDataSource newInstance(){
        if(instance== null){
            instance= new LoanMemLocalDataSource();
        }
        return instance;
    }

    public void save(Loan loan) {
        dataStore.put(loan.idLoan, loan);
    }

    public void saveList(List<Loan> loans) {
        for (Loan loan : loans) {
            save(loan);
        }
    }

    public Loan findById(String idLoan ) {
        return dataStore.get(idLoan);
    }

    public List<Loan> findAll() {
        return dataStore.values().stream().toList();
    }

    public void delete(String idLoan) {
        dataStore.remove(idLoan);
    }
}
