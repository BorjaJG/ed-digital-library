package com.iesam.digitalibrary.digitalresources.domain;

import java.util.ArrayList;

public interface DigitalResourceRepository {
    // Method to obtain a digital resource by its ID
    DigitalResource obtainDR(String idDigitalResource);

    // Method to list all digital resources
    ArrayList<DigitalResource> list();

    void ObtainDR(String isbn);
}
