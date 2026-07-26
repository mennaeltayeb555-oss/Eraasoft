--استخدمنا LEFT JOIN لأننا نريد إظهار جميع الموظفين حتى لو لم يكن لديهم قسم
SELECT e.employee_id,
       e.first_name,
       e.last_name,
       d.department_name
FROM employees e
LEFT JOIN departments d
ON e.department_id = d.department_id;
--استخدمنا LEFT JOIN لإظهار جميع المنتجات حتى إذا لم تكن مرتبطة بأي Category.
SELECT p.product_id,
       p.product_name,
       c.category_name
FROM products p
LEFT JOIN categories c
ON p.category_id = c.category_id;
--ستخدمنا LEFT JOIN لإظهار جميع الطلاب حتى لو لم يسجلوا في أي Course
-- if its manyto many relationship (student-course)
SELECT s.student_id,
       s.student_name,
       c.course_name
FROM students s
LEFT JOIN courses c
ON s.course_id = c.course_id;
--استخدمنا LEFT JOIN لإظهار جميع الطلبات حتى إذا لم يكن لها Customer مطابق.
SELECT o.order_id,
       o.order_date,
       c.customer_name
FROM orders o
LEFT JOIN customers c
ON o.customer_id = c.customer_id;
--ستخدمنا LEFT JOIN لإظهار جميع الأقسام حتى إذا لم يكن لها مدير
SELECT d.department_id,
       d.department_name,
       m.manager_name
FROM departments d
LEFT JOIN managers m
ON d.manager_id = m.manager_id;
--استخدمنا LEFT JOIN لإظهار جميع الكتب حتى إذا لم يكن لها مؤلف
SELECT b.book_id,
       b.book_title,
       a.author_name
FROM books b
LEFT JOIN authors a
ON b.author_id = a.author_id;
--استخدمنا LEFT JOIN لإظهار جميع الفواتير حتى إذا لم يكن لها Payment
SELECT i.invoice_id,
       i.invoice_date,
       p.payment_status
FROM invoices i
LEFT JOIN payments p
ON i.invoice_id = p.invoice_id;
--استخدمنا LEFT JOIN لإظهار جميع الموظفين حتى إذا لم يتم تعيينهم لأي Project
SELECT e.employee_id,
       e.first_name,
       p.project_name
FROM employees e
LEFT JOIN projects_assigned p
ON e.employee_id = p.employee_id;
