package com.example.employeecrud.service;

import com.example.employeecrud.dto.EmployeeEligibilityFact;
import com.example.employeecrud.dto.EmployeeEligibilityResponse;
import com.example.employeecrud.entity.Employee;
import com.example.employeecrud.exception.DuplicateEmployeeException;
import com.example.employeecrud.exception.EmployeeNotFoundException;
import com.example.employeecrud.repository.EmployeeRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final KieContainer kieContainer;

    public EmployeeService(EmployeeRepository employeeRepository, KieContainer kieContainer) {
        this.employeeRepository = employeeRepository;
        this.kieContainer = kieContainer;
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

        EmployeeEligibilityFact fact = new EmployeeEligibilityFact(empId, age);

        KieSession kieSession = kieContainer.newKieSession();
        try {
            kieSession.insert(fact);
            kieSession.fireAllRules();
        } finally {
            kieSession.dispose();
        }

        return new EmployeeEligibilityResponse(
                fact.getEmpId(),
                fact.getReason(),
                fact.getOutput()
        );
    }
}
