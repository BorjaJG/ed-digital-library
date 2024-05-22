package com.iesam.digitalibrary.user.domain;

public class User {

    // Attributes
    public final String userID;
    public final String name;
    public final String email;
    public final String phoneNumber;
    public final String address;
    public final String registrationDate;
    public final String userType;
    public final String accountStatus;
    public final String loanHistory;
    public final String fines;
    public final String transactionHistory;
    public final String notificationPreferences;
    public final String userRole;
    public final String additionalData;

    // Constructor
    public User(String userID, String name, String email, String phoneNumber,
                String address, String registrationDate, String userType, String accountStatus,
                String loanHistory, String fines, String transactionHistory,
                String notificationPreferences, String userRole, String additionalData) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registrationDate = registrationDate;
        this.userType = userType;
        this.accountStatus = accountStatus;
        this.loanHistory = loanHistory;
        this.fines = fines;
        this.transactionHistory = transactionHistory;
        this.notificationPreferences = notificationPreferences;
        this.userRole = userRole;
        this.additionalData = additionalData;
    }

    // toString method to represent object as a String
    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", userType='" + userType + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", loanHistory='" + loanHistory + '\'' +
                ", fines='" + fines + '\'' +
                ", transactionHistory='" + transactionHistory + '\'' +
                ", notificationPreferences='" + notificationPreferences + '\'' +
                ", userRole='" + userRole + '\'' +
                ", additionalData='" + additionalData + '\'' +
                '}';
    }
}
