package com.iesam.digitalibrary.digitalresources.data.local;


import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.user.domain.User;

import java.util.ArrayList;

public interface DigitalResourcesLocalDataSource {

    ArrayList<DigitalResource> findAll();

    DigitalResource findById(String id);


}
