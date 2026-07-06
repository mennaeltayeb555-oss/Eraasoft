CREATE TABLE Employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    CHECK (age >= 18)
);
--Write a SQL query to enforce that salary in a Staff table must be between 3000 and 10000

CREATE TABLE Staff(
    id INT PRIMARY KEY,
    salary DECIMAL(10,2)
    CHECK(salary BETWEEN 3000 AND 10000)
);
--Add a CHECK constraint to an existing table Products that ensures price is greater than 0.--

ALTER TABLE Products
ADD CONSTRAINT chk_price
CHECK(price>0);

--table student 
CREATE TABLE Students(
    id INT PRIMARY KEY,
    name VARCHAR(100),
    grade CHAR(1)
    CHECK(grade IN('A','B','C','D','E','F'))
);
--Add a NOT NULL constraint to the email column in the Customers table
ALTER TABLE Customers
ALTER COLUMN email SET NOT NULL;
--2. Add a UNIQUE constraint to the username column in the Users table.
-- Username مينفعش يتكرر.
ALTER TABLE Users
ADD CONSTRAINT uk_username
UNIQUE(username);
-- Add a FOREIGN KEY constraint on Orders.customer_id referencing Customers(id)
ALTER TABLE Orders
ADD CONSTRAINT fk_order_customer
FOREIGN KEY(customer_id)
REFERENCES Customers(id);
--Add a CHECK constraint to the Accounts table
ALTER TABLE Accounts
ADD CONSTRAINT chk_balance
CHECK(balance >= 0);
--Add a PRIMARY KEY constraint to the Departments table on the dept_id column
ALTER TABLE Departments
ADD CONSTRAINT pk_department
PRIMARY KEY(dept_id);
--Drop a CHECK constraint named chk_salary from Employees
ALTER TABLE Employees
DROP CONSTRAINT chk_salary;
--Remove the UNIQUE constraint on email from Users
ALTER TABLE Users
DROP CONSTRAINT uk_email;
--Drop the PRIMARY KEY from Products
ALTER TABLE Products
DROP CONSTRAINT Products_pkey; --default name 
--Drop FOREIGN KEY named fk_order_customer
ALTER TABLE Orders
DROP CONSTRAINT fk_order_customer;
--Remove NOT NULL from phone column
ALTER TABLE Contacts
ALTER COLUMN phone DROP NOT NULL;
------------------Rename Constraints--------------
ALTER TABLE Students
RENAME CONSTRAINT chk_age
TO check_min_age;
-------------------------
ALTER TABLE Employees
RENAME CONSTRAINT fk_emp_dept
TO fk_employee_department;
-------------------------
ALTER TABLE Users
RENAME CONSTRAINT Users_pkey
TO pk_users_id;
-----------------------------
ALTER TABLE Users
RENAME CONSTRAINT uk_username
TO uk_user_name;
---PostgreSQL does not support disabling constraints directly like SQL Server
--إذا كانت الـ Constraint اتشالت (DROP CONSTRAINT) فالحل هو إضافتها مرة تانية باستخدام:


