package com.iesam.digitalibrary.digitalresources.data.local;



import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DigitalResourcesMemLocalDataSource {


    private Map<String, DigitalResource> dataStore = new TreeMap<>();
    private static DigitalResourcesMemLocalDataSource instance=null;
    public DigitalResourcesMemLocalDataSource newInstance(){
        if(instance== null){
            instance= new DigitalResourcesMemLocalDataSource();
        }
        return instance;
    }

    public void save(DigitalResource DigitalResource) {
        dataStore.put(DigitalResource.idDigitalResource, DigitalResource);
    }

    public void saveList(List<DigitalResource> digitalResources) {
        for (DigitalResource digitalResource : digitalResources) {
            save(digitalResource);
        }
    }

    public DigitalResource findById(String idDigitalResource ) {
        return dataStore.get(idDigitalResource);
    }

    public List<DigitalResource> findAll() {
        return dataStore.values().stream().toList();
    }

    public void delete(String UserId) {
        dataStore.remove(UserId);
    }
}
