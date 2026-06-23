--Q1. Create a table STUDENTS with columns: name, marks
CREATE TABLE students (
    name VARCHAR(50),
    marks INT
);
--Q2. Insert 5 sample students with marks
INSERT INTO students VALUES ('Ahmed', 95);
INSERT INTO students VALUES ('Menna', 85);
INSERT INTO students VALUES ('Ali', 75);
INSERT INTO students VALUES ('Sara', 68);
INSERT INTO students VALUES ('Mona', 90);
--Q3. Use CASE to assign grades
SELECT
    name,
    marks,
    CASE
        WHEN marks >= 90 THEN 'A'
        WHEN marks >= 80 THEN 'B'
        WHEN marks >= 70 THEN 'C'
        ELSE 'F'
    END AS grade
FROM students;