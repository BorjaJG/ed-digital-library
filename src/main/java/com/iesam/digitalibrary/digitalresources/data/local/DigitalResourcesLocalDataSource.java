package com.iesam.digitalibrary.digitalresources.data.local;


import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;

import java.util.ArrayList;

public interface DigitalResourcesLocalDataSource {

    ArrayList<DigitalResource> findAll();



}
