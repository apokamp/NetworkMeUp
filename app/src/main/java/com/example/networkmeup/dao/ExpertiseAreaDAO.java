package com.example.networkmeup.dao;

import com.example.networkmeup.domain.ExpertiseArea;

import java.util.ArrayList;

public interface ExpertiseAreaDAO {
    public ArrayList<ExpertiseArea> getAll();
    public void add(ExpertiseArea expertiseArea);
    public boolean find(ExpertiseArea expertiseArea);
}
