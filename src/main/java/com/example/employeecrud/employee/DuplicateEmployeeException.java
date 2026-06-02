package com.example.employeecrud.employee;

public class DuplicateEmployeeException extends RuntimeException {

    public DuplicateEmployeeException(Long empId) {
        super("Employee already exists with empId: " + empId);
    }
}
