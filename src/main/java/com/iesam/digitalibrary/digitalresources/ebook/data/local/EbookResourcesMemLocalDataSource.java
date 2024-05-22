package com.iesam.digitalibrary.digitalresources.ebook.data.local;


import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EbookResourcesMemLocalDataSource {
    // In-memory storage for ebooks
    private Map<String, Ebook> dataStore = new TreeMap<>();
    // Singleton instance
    private static EbookResourcesMemLocalDataSource instance = null;

    // Method to get a singleton instance
    public static EbookResourcesMemLocalDataSource newInstance() {
        if (instance == null) {
            instance = new EbookResourcesMemLocalDataSource();
        }
        return instance;
    }

    // Save an ebook to the data store
    public void save(Ebook ebook) {
        dataStore.put(ebook.isbn, ebook);
    }

    // Save a list of ebooks to the data store
    public void saveList(List<Ebook> ebooks) {
        for (Ebook ebook : ebooks) {
            save(ebook);
        }
    }

    // Find an ebook by its ID (ISBN in this case)
    public Ebook findById(String idEbook) {
        return dataStore.get(idEbook);
    }

    // Find all ebooks in the data store
    public List<Ebook> findAll() {
        return new ArrayList<>(dataStore.values());
    }

    // Delete an ebook by its ID (ISBN in this case)
    public void delete(String idEbook) {
        dataStore.remove(idEbook);
    }
}
