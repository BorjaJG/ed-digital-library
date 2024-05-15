package com.iesam.digitalibrary.digitalresources.domain;

public class DigitalResource {


    public final String  idDigitalResource;


    public DigitalResource(String idDigitalResource) {
        this.idDigitalResource = idDigitalResource;
    }

    @Override
    public String toString() {
        return "Digitalresources{" +
                "idDigitalResource='" + idDigitalResource + '\'' +
                '}';
    }
}
