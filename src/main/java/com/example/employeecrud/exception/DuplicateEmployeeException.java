package com.example.employeecrud.exception;

public class DuplicateEmployeeException extends RuntimeException {

    public DuplicateEmployeeException(Long empId) {
        super("Employee already exists with empId: " + empId);
    }
}
