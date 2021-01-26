package com.omerfarukalaca.mamonsujavaserver.repository;

import com.omerfarukalaca.mamonsujavaserver.model.MamonsuData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 
 
@Repository
public interface MamonsuDataRepository extends CrudRepository<MamonsuData, Integer>{
 
}
