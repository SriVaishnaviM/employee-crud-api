package com.example.employeecrud.dto;

public class EmployeeEligibilityFact {

    private Long empId;
    private int age;
    private String reason;
    private String output;

    public EmployeeEligibilityFact(Long empId, int age) {
        this.empId = empId;
        this.age = age;
    }

    public Long getEmpId() {
        return empId;
    }

    public int getAge() {
        return age;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}