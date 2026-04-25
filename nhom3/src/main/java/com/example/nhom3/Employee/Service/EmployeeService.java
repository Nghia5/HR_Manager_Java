package com.example.nhom3.Employee.Service;

import com.example.nhom3.Employee.Model.entity.Employee;
import com.example.nhom3.Employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(UUID id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createOrUpdateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }
}