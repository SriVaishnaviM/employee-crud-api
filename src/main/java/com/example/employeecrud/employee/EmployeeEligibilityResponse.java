package com.example.employeecrud.employee;

public record EmployeeEligibilityResponse(
        Long empId,
        String reason,
        String output
) {
}
