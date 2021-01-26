package com.omerfarukalaca.mamonsujavaserver.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MamonsuData {
    
    private String host;
    private String key;
    private String value;
    @Id
    private String clock;
}
