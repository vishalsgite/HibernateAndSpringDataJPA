package com.example.demo.services;

import com.example.demo.Entities.DepartmentEntity;
import com.example.demo.Entities.EmployeeEntity;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository,EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }


    public DepartmentEntity getDepartmentById(Long Id){
        return departmentRepository.findById(Id).orElse(null);
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {

        Optional<DepartmentEntity> departmentEntity =
                 departmentRepository.findById(departmentId);

        Optional<EmployeeEntity> employeeEntity =
                employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    department.setManager(employee);
                    return departmentRepository.save(department);
                })).orElse(null);


    }

    public Optional<DepartmentEntity> getDepartmentOfManager(Long empployeeId) {
        return departmentRepository.findById(empployeeId);
    }

    public DepartmentEntity assignWorkerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity =
                departmentRepository.findById(departmentId);

        Optional<EmployeeEntity> employeeEntity =
                employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
               employee.setWorkerDepartment(department);
               employeeRepository.save(employee);

               department.getWorkers().add(employee);
                       return department;
                })).orElse(null);
    }
}
