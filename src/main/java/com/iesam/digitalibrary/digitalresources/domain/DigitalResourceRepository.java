package com.iesam.digitalibrary.digitalresources.domain;

import java.util.ArrayList;
import java.util.List;

public interface DigitalResourceRepository<T extends DigitalResource> {
    // Method to obtain a digital resource by its ID
    T obtainDigitalResource(String idDigitalResource);

    // Method to list all digital resources
    List<T> list();

    void modify(T digitalResource);

    void delete(String idDigitalResource);

    void save(T digitalResource);
}
