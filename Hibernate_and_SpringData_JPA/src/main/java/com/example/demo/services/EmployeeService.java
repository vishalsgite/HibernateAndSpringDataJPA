package com.example.demo.services;

import com.example.demo.Entities.EmployeeEntity;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity createNewEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    public Optional<EmployeeEntity> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }
}
