package com.mithuns.jpa.service;

import com.mithuns.jpa.ResouceNotFoundException;
import com.mithuns.jpa.model.Employee;
import com.mithuns.jpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    @Transactional
    public Employee update(Employee employee) {
        repository.findById(employee.getId()).orElseThrow(
                () -> new ResouceNotFoundException("Employee not found :: " + employee.getId()));
        final Employee updatedEmployee = repository.save(employee);
        return updatedEmployee;
    }

    @Override
    @Transactional
    public Employee get(int id) {
        return repository.findById(id).orElseThrow(
                () -> new ResouceNotFoundException("Employee not found :: " + id));
    }

    @Override
    @Transactional
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void delete(int id) {
        Employee employee = repository.findById(id).orElseThrow(
                () -> new ResouceNotFoundException("Employee not found :: " + id));
        repository.delete(employee);
    }
}
