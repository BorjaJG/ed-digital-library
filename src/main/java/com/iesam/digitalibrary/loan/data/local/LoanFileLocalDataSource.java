package com.iesam.digitalibrary.loan.data.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesam.digitalibrary.loan.domain.Loan;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class LoanFileLocalDataSource implements LoanLocalDataSource {
    // File name for storing loan data
    private final String folderName = "filestore";
    private final String fileName = "Loan.txt";
    private final String filePath = folderName + File.separator + fileName;

    // Gson instance for JSON serialization/deserialization
    private Gson gson = new Gson();

    // Type token for Gson to deserialize ArrayList<Loan>
    private final Type typeList = new TypeToken<ArrayList<Loan>>() {
    }.getType();

    // Save a loan to the file
    @Override
    public void save(Loan loan) {
        List<Loan> loans = findAll();
        loans.add(loan);
        saveToFile(loans);
    }

    // Delete a loan by ID from the file
    @Override
    public void delete(String idLoan) {
        List<Loan> newList = new ArrayList<>();
        List<Loan> loans = findAll();
        for (Loan loan : loans) {
            if (!loan.idLoan.equals(idLoan)) {
                newList.add(loan);
            }
        }
        saveList(newList);
    }

    // Save a list of loans to the file
    public void saveList(List<Loan> loans) {
        saveToFile(loans);
    }

    // Internal method to save data to the file
    private void saveToFile(List<Loan> loans) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(gson.toJson(loans));
            myWriter.close();
            System.out.println("Data saved successfully");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data.");
            e.printStackTrace();
        }
    }

    // Find a loan by ID from the file
    @Override
    public Loan findById(String id) {
        List<Loan> loans = findAll();
        for (Loan loan : loans) {
            if (Objects.equals(loan.idLoan, id)) {
                return loan;
            }
        }
        return null;
    }

    // Find all loans from the file
    @Override
    public ArrayList<Loan> findAll() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                myReader.close();
                return gson.fromJson(data, typeList);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while retrieving the list.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }

    // Modify a loan in the file
    @Override
    public void modify(Loan loan) {
        delete(loan.idLoan); // Delete the existing loan
        save(loan); // Save the modified loan
    }
}