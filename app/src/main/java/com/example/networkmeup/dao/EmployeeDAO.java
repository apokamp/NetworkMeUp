package com.example.networkmeup.dao;

import com.example.networkmeup.domain.Employee;

import java.util.ArrayList;

public interface EmployeeDAO {
    public void delete(Employee employee);
    public ArrayList<Employee> getAll();
    public void save(Employee employee);
    public boolean find(Employee employee);

}
