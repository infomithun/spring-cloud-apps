package com.mithuns.jpa.repository;

import com.mithuns.jpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Serializable> {
}
