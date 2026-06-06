package com.example.employeecrud.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long empId) {
        super("Employee not found with empId: " + empId);
    }
}
