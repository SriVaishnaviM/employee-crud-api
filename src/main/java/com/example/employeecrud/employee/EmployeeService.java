package com.example.employeecrud.employee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException(empId));
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getEmpId() == null) {
            throw new IllegalArgumentException("Employee ID is required");
        }
        if (employeeRepository.existsById(employee.getEmpId())) {
            throw new DuplicateEmployeeException(employee.getEmpId());
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long empId, Employee employeeDetails) {
        Employee employee = getEmployeeById(empId);
        employee.setEmpName(employeeDetails.getEmpName());
        employee.setAddress(employeeDetails.getAddress());
        employee.setDateOfBirth(employeeDetails.getDateOfBirth());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long empId) {
        Employee employee = getEmployeeById(empId);
        employeeRepository.delete(employee);
    }
}
