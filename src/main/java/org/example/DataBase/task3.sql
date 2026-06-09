
--1. Employees with emp_id in range 100–105
SELECT * FROM employees
WHERE employee_id BETWEEN 100 AND 105;

--2 Employees with specific emp_id set
SELECT * FROM employees
WHERE employee_id IN (151, 152, 153, 154, 155);

--3 first_name starts with 'P' or 'p'
SELECT * FROM employees
WHERE first_name ILIKE 'P%';

--4. first_name ends with 'A' or 'a'
sqlSELECT * FROM employees
WHERE first_name ILIKE '%A';

--5. first_name contains 'A' or 'a' (anywhere)
sqlSELECT * FROM employees
WHERE first_name ILIKE '%A%';

--6. first_name third character is 'e' or 'E'
sqlSELECT * FROM employees
WHERE first_name ILIKE '__e%';

--7. Employees without a manager (manager_id is NULL)
sqlSELECT * FROM employees
WHERE manager_id IS NULL;

--8. Employees with a manager assigned
sqlSELECT * FROM employees
WHERE manager_id IS NOT NULL;

--9.Insert a new employee without a manager
INSERT INTO employees (employee_id, first_name, last_name, email, hire_date, job_id, salary, manager_id)
VALUES (207, 'John', 'Doe', 'JDOE', '2024-01-15', 'IT_PROG', 5000.00, NULL);
--10. Employees with job_id of 'AD_VP' or 'IT_PROG'
SELECT * FROM employees
WHERE job_id IN ('AD_VP', 'IT_PROG');
--11. Employees sorted by last_name ascending
sqlSELECT * FROM employees
ORDER BY last_name ASC;

--12. Employees sorted by hire_date descending
sqlSELECT * FROM employees
ORDER BY hire_date DESC;

--13. Sort by department_id ASC, then salary DESC
sqlSELECT * FROM employees
ORDER BY department_id ASC, salary DESC;

--14. last_name in lowercase
sqlSELECT employee_id, LOWER(last_name) AS last_name
FROM employees;
--15. first_name in uppercase
sqlSELECT employee_id, UPPER(first_name) AS first_name
FROM employees;

--16. first_name and last_name in title case
sqlSELECT employee_id,
       INITCAP(first_name) AS first_name,
       INITCAP(last_name)  AS last_name
FROM employees;

--17. Employees whose last_name is 'smith' (case-insensitive)
sqlSELECT * FROM employees
WHERE last_name ILIKE 'smith';