-- 1) Self join: using aliases e (employee)
SELECT e.name AS employee_name, m.name AS manager_name
FROM employees e
JOIN employees m ON e.manager_id = m.employee_id;
-- 2) Both customers and employees have a "name" column with different
--  (customer / salesperson)
SELECT c.name AS customer_name, e.name AS salesperson_name
FROM customers c
JOIN employees e ON c.salesperson_id = e.employee_id;
-- 3) Both orders and order_details have an order_id column
SELECT o.order_id, od.product_id
FROM orders o
JOIN order_details od ON o.order_id = od.order_id;
-- 4) Both students and instructors have a "name" column
-- aliases resolve the ambiguity explicitly
SELECT s.name AS student_name, i.name AS instructor_name
FROM students s
JOIN instructors i ON s.instructor_id = i.instructor_id;
-- 5) employees.salary and departments.budget isn't same a name
SELECT e.name, e.salary, d.budget
FROM employees e
JOIN departments d ON e.department_id = d.department_id;
-- 6) Both projects and tasks have a "name" column
SELECT p.name AS project_name, t.name AS task_name
FROM projects p
JOIN tasks t ON p.project_id = t.project_id;
-- 7) Both courses and exams have a "date" column with different 
-- (course start date / exam date)
SELECT c.date AS course_date, ex.date AS exam_date
FROM courses c
JOIN exams ex ON c.course_id = ex.course_id;
-- 8) Both products and categories have a "name" column;
-- aliases p/cat distinguish product name from category name
SELECT p.name AS product_name, cat.name AS category_name
FROM products p
JOIN categories cat ON p.category_id = cat.category_id;
-- 9) books.title and publishers.name don't actually conflict
-- but aliases b/pub are used for clarity and consistency
SELECT b.title AS book_title, pub.name AS publisher_name
FROM books b
JOIN publishers pub ON b.publisher_id = pub.publisher_id;
-- 10) Both employees and departments have a "location" column;
-- aliases e/d resolve which location to the employee / the department
SELECT e.name, d.location AS department_location
FROM employees e
JOIN departments d ON e.department_id = d.department_id;