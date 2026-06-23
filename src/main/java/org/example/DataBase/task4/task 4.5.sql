--Q1
SELECT first_name, last_name, job_id, salary,
       DECODE(job_id,
              'ST_CLERK', salary * 1.2,
              'SA_REP',   salary * 1.3,
              'IT_PROG',  salary * 1.5,
                           salary) AS updated_salary
FROM employees;
--Q2. Create a table ORDERS with a column status ('P', 'S', 'D')
CREATE TABLE orders (
    order_id NUMBER,
    status CHAR(1)
);
INSERT INTO orders VALUES (1, 'P');
INSERT INTO orders VALUES (2, 'S');
INSERT INTO orders VALUES (3, 'D');
--Q3. Use DECODE to display full form of status
SELECT order_id,
       status,
       DECODE(status,
              'P', 'Pending',
              'S', 'Shipped',
              'D', 'Delivered') AS status_name
FROM orders;