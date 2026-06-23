--1. AVG Function
SELECT AVG(salary)
FROM employees;
--2. COUNT Function
SELECT COUNT(*)
FROM employees;
--3. MAX Function
SELECT MAX(salary)
FROM employees;
--4. MIN Function
SELECT MIN(salary)
FROM employees;
--5. SUM Function
SELECT SUM(salary)
FROM employees;
--6. GROUP BY (Part 1)
SELECT department,
       AVG(salary)
FROM employees
GROUP BY department;
--7. GROUP BY (Part 2)
SELECT job_id,
       COUNT(*)
FROM employees
GROUP BY job_id;
--8. HAVING Clause
SELECT department,
       SUM(salary)
FROM employees
GROUP BY department
HAVING SUM(salary) > 50000;
--9.AVG Function
SELECT AVG(commission_pct)
FROM employees
WHERE commission_pct IS NOT NULL;
--10. COUNT Function 
SELECT COUNT(*)
FROM employees
WHERE salary > 10000;
--11. MAX and MIN Together
SELECT job_id,
       MAX(salary),
       MIN(salary)
FROM employees
GROUP BY job_id;
--12. SUM Function
SELECT manager_id,
       SUM(salary)
FROM employees
GROUP BY manager_id;
--13. GROUP BY with Multiple Columns
SELECT department,
       job_id,
       SUM(salary)
FROM employees
GROUP BY department, job_id;
--14. HAVING with COUNT
SELECT job_id,
       COUNT(*)
FROM employees
GROUP BY job_id
HAVING COUNT(*) > 5;
--15. Using Aggregate Functions Together--**
SELECT department,
       COUNT(*) AS total_employees,
       AVG(salary) AS average_salary,
       MAX(salary) AS maximum_salary,
       MIN(salary) AS minimum_salary
FROM employees
GROUP BY department;
--16. Complex HAVING
SELECT department
FROM employees
GROUP BY department
HAVING AVG(salary) > 8000
   AND COUNT(*) < 10;
--17. Nested Aggregation
SELECT department,
       SUM(salary) AS total_salary
FROM employees
GROUP BY department
ORDER BY total_salary DESC
LIMIT 1;
--18. Using Aliases with Aggregates--**
SELECT department,
       SUM(salary) AS total_salary,
       AVG(salary) AS average_salary
FROM employees
GROUP BY department;