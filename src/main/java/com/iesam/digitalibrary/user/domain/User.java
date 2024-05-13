package com.iesam.digitalibrary.user.domain;

public class User {


    /*
       Los atributos incluyen la identificación del usuario, su nombre, correo electrónico, número de teléfono,
       dirección, fecha de registro, tipo de usuario, estado de la cuenta, historial de préstamos, multas,
       historial de transacciones, preferencias de notificación, rol de usuario y datos adicionales.
       Estos son datos clave para gestionar la información del usuario en el sistema.
     */

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



    public String toStringCarnet() {
        StringBuilder sb = new StringBuilder();
        sb.append("───────────────────────────────────\n");
        sb.append("        CARNET\n");
        sb.append("───────────────────────────────────\n");
        sb.append("  User ID:        ").append(userID).append("\n");
        sb.append("  Name:           ").append(name).append("\n");
        sb.append("  Email:          ").append(email).append("\n");
        sb.append("  Phone Number:   ").append(phoneNumber).append("\n");
        sb.append("  Address:        ").append(address).append("\n");
        sb.append("───────────────────────────────────\n");
        return sb.toString();
    }

}
