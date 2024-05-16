package com.iesam.digitalibrary.digitalresources.domain;

import java.util.ArrayList;

public interface DigitalResourceRepository {
    void ObtainDR(String idDigitalResource);
    ArrayList<DigitalResource> lits();
}
