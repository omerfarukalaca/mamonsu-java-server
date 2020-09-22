package com.omerfarukalaca.mamonsujavaserver.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MamonsuResponse {
    private String request;
    private List<MamonsuData> data = new ArrayList<MamonsuData>();
    private String clock;
}