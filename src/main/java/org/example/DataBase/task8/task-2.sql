--i inserted hr schema here to wrire with postgresql |
DROP TABLE IF EXISTS job_history, jobs, departments CASCADE;

CREATE TABLE jobs (
    job_id VARCHAR(10) PRIMARY KEY,
    job_title VARCHAR(50),
    min_salary NUMERIC,
    max_salary NUMERIC
);

CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50)
);

CREATE TABLE job_history (
    employee_id INT,
    start_date DATE,
    end_date DATE,
    job_id VARCHAR(10),
    department_id INT
);

INSERT INTO jobs VALUES
('AD_ASST','Administration Assistant',3000,6000),
('FI_MGR','Finance Manager',8200,16000),
('FI_ACCOUNT','Accountant',4200,9000),
('AC_MGR','Accounting Manager',8200,16000),
('AC_ACCOUNT','Public Accountant',4200,9000),
('SA_MAN','Sales Manager',10000,20000),
('SA_REP','Sales Representative',6000,12000),
('PU_MAN','Purchasing Manager',8000,15000),
('IT_PROG','Programmer',4000,10000);

INSERT INTO departments VALUES
(10,'Administration'), (20,'Marketing'), (30,'Purchasing'),
(40,'Human Resources'), (50,'Shipping'), (60,'IT');

INSERT INTO job_history VALUES
(101,'2020-01-01','2021-01-01','AD_ASST',10),
(102,'2019-05-01','2020-06-01','FI_MGR',NULL),
(103,'2018-03-01','2019-03-01','SA_REP',20),
(104,'2017-01-01','2018-01-01','PU_MAN',30),
(105,'2021-01-01','2022-01-01','IT_PROG',60);
--------------------------joins---------------------------------------------
-- 1) NATURAL JOIN: works because job_id is named identically in both tables
SELECT job_history.employee_id, jobs.job_title
FROM job_history
NATURAL JOIN jobs;
-- 2) USING: explicit choice of job_id as the join key, avoids duplicate column
SELECT job_history.employee_id, jobs.job_title
FROM job_history
JOIN jobs USING (job_id);
-- 3) ON: same result as USING here, but ON is used because it's more flexible
-- (works even if column names differed between the two tables)
SELECT job_history.employee_id, jobs.job_title
FROM job_history
JOIN jobs ON job_history.job_id = jobs.job_id;
-- 4) INNER JOIN (explicit keyword): only returns rows where job_id matches
-- in both tables (same as plain JOIN above)
SELECT job_history.employee_id, jobs.job_title
FROM job_history
INNER JOIN jobs ON job_history.job_id = jobs.job_id;
-- 5) LEFT JOIN: returns ALL job_history rows even if a job_id has no
-- matching row in jobs (job_title would show as NULL in that case)
SELECT job_history.employee_id, jobs.job_title
FROM job_history
LEFT JOIN jobs ON job_history.job_id = jobs.job_id;
-- 6) RIGHT JOIN: returns ALL jobs rows even if a job was never held
-- historically (employee_id would show as NULL in that case)
SELECT job_history.employee_id, jobs.job_title
FROM job_history
RIGHT JOIN jobs ON job_history.job_id = jobs.job_id;
-- 7) FULL JOIN: returns everything from both tables — matched rows,
-- job_history rows with no matching job, AND jobs with no history record
SELECT job_history.employee_id, jobs.job_title
FROM job_history
FULL JOIN jobs ON job_history.job_id = jobs.job_id;
