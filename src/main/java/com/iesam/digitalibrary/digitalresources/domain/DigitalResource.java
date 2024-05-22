package com.iesam.digitalibrary.digitalresources.domain;

public class DigitalResource {


    // Unique identifier for the digital resource
    public final String idDigitalResource;

    // Constructor to initialize the digital resource with its ID
    public DigitalResource(String idDigitalResource) {
        this.idDigitalResource = idDigitalResource;
    }

    // Override toString method to provide a string representation of the digital resource
    @Override
    public String toString() {
        return "DigitalResource{" +
                "idDigitalResource='" + idDigitalResource + '\'' +
                '}';
    }
}
