CREATE TABLE customers (
    full_name VARCHAR(50)
);
INSERT INTO customers VALUES ('   Ahmed Ali   ');
INSERT INTO customers VALUES ('   Menna');
INSERT INTO customers VALUES ('Sara   ');
--remove spaces 
SELECT full_name,
       TRIM(full_name) AS trimmed_name
FROM customers;
--remove spaces from left only
SELECT full_name,
       LTRIM(full_name) AS left_trim
FROM customers;
--remove spaces from right only
SELECT full_name,
       RTRIM(full_name) AS right_trim
FROM customers;
--remove symbol
SELECT TRIM(BOTH '$' FROM '$$$Ahmed$$$');
SELECT TRIM(BOTH '*' FROM '***Ali***');
--(REPLACE, LPAD, RPAD)
SELECT REPLACE('promotion','o','0');
--replace keyword
SELECT REPLACE('This is a basic course',
               'basic',
               'advanced');
--              create table department------- 
CREATE TABLE departments (
    dept_name VARCHAR(20)
);
INSERT INTO departments VALUES ('IT');
INSERT INTO departments VALUES ('HR');
INSERT INTO departments VALUES ('Sales');
--used LPAD
SELECT dept_name,
       LPAD(dept_name,15,'*')
FROM departments;
--used RPAD
SELECT dept_name,
       RPAD(dept_name,15,'-')
FROM departments;
--DD-MON-YYYY
SELECT TO_CHAR(CURRENT_DATE,'DD-MON-YYYY');
--Day, Month YYYY
SELECT TO_CHAR(CURRENT_DATE,'Day, Month YYYY');
------
SELECT TO_CHAR(1234567.89,'9,999,999.99');
SELECT TO_CHAR(5000,'$9,999.99');
--display current date and time 
SELECT TO_CHAR(NOW(),
               'YYYY/MM/DD HH24:MI:SS');
-------------4. CASE Expressions---------------------------
CREATE TABLE student (
    name VARCHAR(20),
    score INT
);
INSERT INTO student VALUES ('Ahmed',95);
INSERT INTO student VALUES ('Ali',85);
INSERT INTO student VALUES ('Sara',75);
INSERT INTO student VALUES ('Menna',65);
INSERT INTO student VALUES ('Omar',50);
--report
SELECT name,
       score,
       CASE
           WHEN score >= 90 THEN 'A'
           WHEN score >= 80 THEN 'B'
           WHEN score >= 70 THEN 'C'
           ELSE 'F'
       END AS grade
FROM student;
--Pass / Fail
SELECT name,
       score,
       CASE
           WHEN score >= 60 THEN 'Pass'
           ELSE 'Fail'
       END AS result
FROM student;
--messages for grades 
SELECT name,
       score,
       CASE
           WHEN score >= 90 THEN 'Excellent'
           WHEN score >= 80 THEN 'Good'
           WHEN score >= 70 THEN 'Average'
           ELSE 'Needs Improvement'
       END AS message
FROM student;
--name of the cuurent day
SELECT CASE
       WHEN TO_CHAR(CURRENT_DATE,'Day') LIKE 'Monday%' THEN 'Today is Monday'
       WHEN TO_CHAR(CURRENT_DATE,'Day') LIKE 'Tuesday%' THEN 'Today is Tuesday'
       WHEN TO_CHAR(CURRENT_DATE,'Day') LIKE 'Wednesday%' THEN 'Today is Wednesday'
       ELSE 'Another Day'
       END;
--5. DECODE Function postgreSQL dont support decode 
--then i used case 
SELECT score,
       CASE score
            WHEN 100 THEN 'A'
            WHEN 90 THEN 'B'
            WHEN 80 THEN 'C'
            ELSE 'F'
       END
FROM student;
----------------------create table status_log 
CREATE TABLE status_log (
    status_code CHAR(1)
);
INSERT INTO status_log VALUES ('N');
INSERT INTO status_log VALUES ('I');
INSERT INTO status_log VALUES ('C');

--code transform
SELECT status_code,
       CASE status_code
            WHEN 'N' THEN 'New'
            WHEN 'I' THEN 'In Progress'
            WHEN 'C' THEN 'Completed'
       END AS status
FROM status_log;
--quantiy
SELECT quantity,
       CASE
           WHEN quantity = 0 THEN 'Out of Stock'
           ELSE 'Available'
       END
FROM products;
--bonus for department 
SELECT department,
       CASE
           WHEN department='HR' THEN 500
           WHEN department='IT' THEN 1000
           WHEN department='Sales' THEN 1500
           ELSE 300
       END AS bonus
FROM employees;