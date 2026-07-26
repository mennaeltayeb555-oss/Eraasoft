CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50)
);

CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR(50),
    department_id INT
);

INSERT INTO departments (department_id, department_name) VALUES
(1, 'IT'),
(2, 'HR'),
(3, 'Sales'),
(4, 'Marketing');  -- قسم من غير أي موظفين قصدًا عشان نشوف الفرق بعدين

INSERT INTO employees (employee_id, name, department_id) VALUES
(101, 'Ahmed', 1),
(102, 'Sara', 2),
(103, 'Omar', 1),
(104, 'Nourhan', 3),
(105, 'Khaled', NULL);  -- موظف من غير قسم قصدًا
--ques1:الجدولين عندهم عمود مشترك بنفس الاسم بالظبط - مفيش عمور متكرر اسمه -يبقا نستخدم الnatural join
SELECT employees.name, departments.department_name
FROM employees
NATURAL JOIN departments;
--Ques2
--insert data to try----
CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(50)
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    order_date DATE
);

INSERT INTO customers (customer_id, customer_name) VALUES
(1, 'Mona'),
(2, 'Youssef'),
(3, 'Laila');  -- عميلة لسه معملتش أي أوردر، عشان نشوف الفرق بعدين

INSERT INTO orders (order_id, customer_id, order_date) VALUES
(501, 1, '2026-01-05'),
(502, 2, '2026-02-10'),
(503, 1, '2026-03-01'),
(504, NULL, '2026-03-15');  -- أوردر من غير عميل معروف
-- Natural join links orders and customers automatically
-- via the shared customer_id column (same name in both tables)
SELECT orders.order_id, customers.customer_name
FROM orders
NATURAL JOIN customers;
--ques3:
-- First natural join links students to enrollments via student_id
-- Second natural join links the result to courses via course_id
SELECT students.name, courses.course_title
FROM students
NATURAL JOIN enrollments
NATURAL JOIN courses;
-- Junction table (project_assignments) links employees to projects
-- many to many relationship
SELECT projects.project_name, employees.name
FROM projects
NATURAL JOIN assignments
-- Junction table (invoice_details) links,invoices,products
SELECT invoices.invoice_id, invoice_details.quantity, products.product_name
FROM invoices
NATURAL JOIN invoice_details
NATURAL JOIN products;
--Books + Author names 
--كل كتاب ليه author_id واحد بيربطه بجدول authors
SELECT books.title, authors.author_name
FROM books
NATURAL JOIN authors;
--Class schedules + Instructors
--جدول schedules فيه instructor_id بيربط على instructor
SELECT schedules.class_name, instructors.instructor_name
FROM schedules
NATURAL JOIN instructors;
--Suppliers + Products they supply
--جدول products فيه supplier_id بيربط على supplier
SELECT suppliers.supplier_name, products.product_name
FROM products
NATURAL JOIN suppliers;
--Customer orders + Shipping details
--جدول shipping_details فيه order_id بيربط على order
SELECT orders.order_id, shipping_details.shipping_address
FROM orders
NATURAL JOIN shipping_details;
--Employees + Job titles
--جدول employees فيه job_id بيربط على job
SELECT employees.name, jobs.job_title
FROM employees
NATURAL JOIN jobs;