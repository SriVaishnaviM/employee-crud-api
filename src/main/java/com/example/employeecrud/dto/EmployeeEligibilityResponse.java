package com.example.employeecrud.dto;

public record EmployeeEligibilityResponse(
        Long empId,
        String reason,
        String output
) {
}
