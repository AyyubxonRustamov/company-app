package com.example.springbootmvc.service;

import com.example.springbootmvc.dto.ApiResponse;
import com.example.springbootmvc.entity.Department;
import com.example.springbootmvc.entity.Employee;
import com.example.springbootmvc.repository.DepartmentRepository;
import com.example.springbootmvc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    public ApiResponse add(Employee employee) {
        Optional<Department> optionalDepartment = departmentRepository.findById(employee.getDepartment().getId());
        if (optionalDepartment.isEmpty()) return new ApiResponse("Akaajon bunaqa id yoq", false);
        Department department = optionalDepartment.get();

        Department save = departmentRepository.save(department);
        return new ApiResponse("Saved", true, save);
    }
}
