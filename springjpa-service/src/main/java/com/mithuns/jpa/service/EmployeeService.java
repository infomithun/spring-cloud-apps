package com.mithuns.jpa.service;

import com.mithuns.jpa.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {

    Employee save(Employee employee);
    Employee update(Employee employee);
    Employee get(int id);
    List<Employee> getAll();
    void delete(int id);
}
