package com.example.employeecrud.employee;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{empId}")
    public Employee getEmployeeById(@PathVariable Long empId) {
        return employeeService.getEmployeeById(empId);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping("/{empId}")
    public Employee updateEmployee(@PathVariable Long empId, @Valid @RequestBody Employee employee) {
        return employeeService.updateEmployee(empId, employee);
    }

    @DeleteMapping("/{empId}")
    public Map<String, String> deleteEmployee(@PathVariable Long empId) {
        employeeService.deleteEmployee(empId);
        return Map.of("message", "Employee deleted successfully", "empId", empId.toString());
    }
}
