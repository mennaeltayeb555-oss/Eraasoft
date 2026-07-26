-- 1) USING(department_id): both tables share the exact same column name,
-- so USING avoids repeating the join condition and merges the column into one
SELECT employees.name, departments.department_name
FROM employees
JOIN departments USING (department_id);
-- 2) USING(customer_id): explicit join column chosen on purpose instead of
-- NATURAL JOIN, in case more shared-name columns get added to either table later
SELECT orders.order_id, customers.customer_name
FROM orders
JOIN customers USING (customer_id);
-- 3) USING(supplier_id): links each product to its supplier;
-- USING keeps supplier_id from appearing twice in the result
SELECT products.product_name, suppliers.supplier_name
FROM products
JOIN suppliers USING (supplier_id);
-- 4) USING(student_id): joins students to their enrollment records first;
-- a second USING(course_id) is needed to reach course_title since it's
-- a many-to-many relationship through the enrollments junction table
SELECT students.name, courses.course_title
FROM students
JOIN enrollments USING (student_id)
JOIN courses USING (course_id);
-- 5) USING(product_id): connects each invoice to the product it's billed for
SELECT invoices.invoice_number, products.product_name
FROM invoices
JOIN products USING (product_id);
-- 6) USING(project_id): links project assignments to project names;
-- a second USING(employee_id) reaches the employee name, since assignments
-- is the junction table between projects and employees
SELECT projects.project_name, employees.name
FROM projects
JOIN project_employees USING (project_id)
JOIN employees USING (employee_id);
-- 7) USING(author_id): connects each book to its author directly (one-to-many)
SELECT authors.author_name, books.title
FROM books
JOIN authors USING (author_id);
-- 8) USING(employee_id): shows which employee handled each sales order
SELECT sales_orders.sales_order_id, sales_orders.order_details, employees.name
FROM sales_orders
JOIN employees USING (employee_id);
-- 9) USING(instructor_id): links each class schedule to its instructor
SELECT course_schedules.class_time, instructors.instructor_name
FROM course_schedules
JOIN instructors USING (instructor_id);
-- 10) USING(account_id): links each transaction to the account holder's name
SELECT transactions.transaction_id, transactions.amount, accounts.account_holder
FROM transactions
JOIN accounts USING (account_id);
