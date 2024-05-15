package com.iesam.digitalibrary.digitalresources.ebook.data.local;



import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;
import com.iesam.digitalibrary.user.domain.User;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EbookResourcesMemLocalDataSource {

    private Map<String, Ebook> dataStore = new TreeMap<>();
    private static EbookResourcesMemLocalDataSource instance=null;
    public EbookResourcesMemLocalDataSource newInstance(){
        if(instance== null){
            instance= new EbookResourcesMemLocalDataSource();
        }
        return instance;
    }

        public void save(Ebook ebook) {
        dataStore.put(ebook.isbn, ebook);
    }

    public void saveList(List<Ebook> ebooks) {
        for (Ebook ebook : ebooks) {
            save(ebook);
        }
    }

    public Ebook findById(String idEbook) {
        return dataStore.get(idEbook);
    }

    public List<Ebook> findAll() {
        return dataStore.values().stream().toList();
    }

    public void delete(String idEbook) {
        dataStore.remove(idEbook);
    }
}
