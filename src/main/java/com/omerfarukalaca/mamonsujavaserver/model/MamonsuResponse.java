package com.omerfarukalaca.mamonsujavaserver.model;

import java.util.ArrayList;
import java.util.List;

public class MamonsuResponse {
    String request;
    List<MamonsuData> data = new ArrayList<MamonsuData>();
    String clock;
}