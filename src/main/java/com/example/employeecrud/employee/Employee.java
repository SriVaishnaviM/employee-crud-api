package com.example.employeecrud.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @NotNull(message = "Employee ID is required")
    @Column(name = "emp_id")
    private Long empId;

    @NotBlank(message = "Employee name is required")
    @Column(name = "emp_name", nullable = false, length = 100)
    private String empName;

    @NotBlank(message = "Address is required")
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    public Employee() {
    }

    public Employee(Long empId, String empName, String address, LocalDate dateOfBirth) {
        this.empId = empId;
        this.empName = empName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
