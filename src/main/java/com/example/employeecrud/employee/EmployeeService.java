package com.example.employeecrud.employee;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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

    public EmployeeEligibilityResponse getEmployeeEligibility(Long empId) {
        Employee employee = getEmployeeById(empId);
        int age = Period.between(employee.getDateOfBirth(), LocalDate.now()).getYears();

        if (age < 25) {
            return new EmployeeEligibilityResponse(
                    empId,
                    "Employee age is " + age + ", which is below 25",
                    "ineligible"
            );
        }

        if (age <= 45) {
            return new EmployeeEligibilityResponse(
                    empId,
                    "Employee age is " + age + ", which is between 25 and 45",
                    "eligible"
            );
        }

        if (age < 55) {
            return new EmployeeEligibilityResponse(
                    empId,
                    "Employee age is " + age + ", which is between 46 and 54",
                    "still consideration"
            );
        }

        return new EmployeeEligibilityResponse(
                empId,
                "Employee age is " + age + ", which is 55 or above",
                "ineligible"
        );
    }
}
