CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Department VARCHAR(50),
    Salary NUMERIC(10,2)
);
-- Insert 5 records
INSERT INTO Employees (EmployeeID, FirstName, LastName, Department, Salary) VALUES
(101, 'John1', 'Doe1', 'HR', 20000),
(102, 'John2', 'Doe2', 'IT', 50000),
(103, 'John3', 'Doe3', 'CS', 40000),
(104, 'John4', 'Doe4', 'IT', 10000),
(105, 'John5', 'Doe5', 'ZX', 30000);
-- Update salary for EmployeeID 101
UPDATE Employees 
SET Salary = 600000 
- Delete employee with EmployeeID 101
DELETE FROM Employees 
WHERE EmployeeID = 101;
-- Show all IT department employees
SELECT * FROM Employees 
WHERE Department = 'IT';
-- Show all data with Full Name
SELECT 
    EmployeeID,
    FirstName || ' ' || LastName AS FullName,
    Department,
    Salary
FROM Employees;