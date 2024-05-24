package com.iesam.digitalibrary.user.domain;

public class UserFactory {

    public User build(String userId,String name,String email,String phoneNumber,String address,String registrationDate,String userType,String status, String history,String fines,String transactions,String notificationPreference,String roleId,String additionalData) {
        return new User(userId, name, email, phoneNumber, address, registrationDate, userType, status, history, fines, transactions, notificationPreference, roleId, additionalData);
    }

}
