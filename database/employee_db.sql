CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

CREATE TABLE IF NOT EXISTS employee (
    emp_id BIGINT PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL
);

INSERT INTO employee (emp_id, emp_name, address, date_of_birth) VALUES
    (101, 'Aarav Sharma', 'Hyderabad, Telangana', '1995-04-12'),
    (102, 'Priya Patel', 'Bengaluru, Karnataka', '1993-08-24'),
    (103, 'Rahul Verma', 'Mumbai, Maharashtra', '1990-01-15'),
    (104, 'Sneha Reddy', 'Chennai, Tamil Nadu', '1997-11-03')
ON DUPLICATE KEY UPDATE
    emp_name = VALUES(emp_name),
    address = VALUES(address),
    date_of_birth = VALUES(date_of_birth);
