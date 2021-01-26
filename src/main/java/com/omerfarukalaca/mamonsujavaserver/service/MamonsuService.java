package com.omerfarukalaca.mamonsujavaserver.service;

import java.util.ArrayList;
import java.util.List;

import com.omerfarukalaca.mamonsujavaserver.model.MamonsuData;
import com.omerfarukalaca.mamonsujavaserver.repository.MamonsuDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 
@Service
public class MamonsuService {
 
    // @Autowired annotation provides the automatic dependency injection.
    private MamonsuDataRepository repository;

    @Autowired
    public MamonsuService(MamonsuDataRepository repository){
        this.repository = repository;
    }
    
    // Save data entity in the h2 database.
    public void save(final MamonsuData data) {
        repository.save(data);
    }

    // Save dataList in the h2 database.
    public void saveAll(final List<MamonsuData> dataList) {
        dataList.forEach(data -> repository.save(data));
    }
 
    // Get all dataList from the h2 database.
    public List<MamonsuData> getAll() {
        final List<MamonsuData> dataList = new ArrayList<>();
        repository.findAll().forEach(data -> dataList.add(data));
        return dataList;
    }
}
