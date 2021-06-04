package com.mithuns.jpa.controller;

import com.mithuns.jpa.model.Employee;
import com.mithuns.jpa.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/employee/{id}")
    public Employee findEmployeeById(@PathVariable(value = "id") Integer employeeId) {
        log.info("Fetching employee details with id : {}", employeeId);
        return employeeService.get(employeeId);
    }

    @PutMapping("/employee")
    public Employee updateEmployee(@RequestBody Employee employee) {
        log.info("Updating employee details with id : {}", employee.getId());
        return employeeService.update(employee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable(value = "id") Integer employeeId) {
        log.info("Deleting employee with id : {}", employeeId);
        employeeService.delete(employeeId);
    }
}
