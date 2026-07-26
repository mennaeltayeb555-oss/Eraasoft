-- استخدمنا FULL OUTER JOIN لإظهار جميع العملاء وجميع الطلبات حتى لو لم يوجد تطابق.
SELECT c.customer_id,
       c.customer_name,
       o.order_id,
       o.order_date
FROM customers c
FULL OUTER JOIN orders o
ON c.customer_id = o.customer_id;
-- استخدمنا FULL OUTER JOIN لإظهار جميع الموظفين وجميع المشاريع حتى لو لم يوجد تطابق.
SELECT e.employee_id,
       e.first_name,
       p.project_name
FROM employees e
FULL OUTER JOIN projects p
ON e.project_id = p.project_id;
-- استخدمنا FULL OUTER JOIN لإظهار جميع المنتجات وجميع الموردين حتى لو لم يوجد تطابق.
SELECT p.product_name,
       s.supplier_name
FROM products p
FULL OUTER JOIN suppliers s
ON p.supplier_id = s.supplier_id;
-- استخدمنا FULL OUTER JOIN لإظهار جميع الطلاب وجميع الكورسات حتى لو لم يوجد تطابق.
SELECT s.student_name,
       c.course_name
FROM students s
FULL OUTER JOIN courses c
ON s.course_id = c.course_id;
-- استخدمنا FULL OUTER JOIN لإظهار جميع المؤلفين وجميع الكتب حتى لو لم يوجد تطابق.
SELECT a.author_name,
       b.book_title
FROM authors a
FULL OUTER JOIN books b
ON a.author_id = b.author_id;
-- استخدمنا FULL OUTER JOIN لإظهار جميع الموظفين وجميع الأقسام حتى لو لم يوجد تطابق.
SELECT e.first_name,
       d.department_name
FROM employees e
FULL OUTER JOIN departments d
ON e.department_id = d.department_id;
-- استخدمنا FULL OUTER JOIN لإظهار جميع المعاملات وجميع طرق الدفع حتى لو لم يوجد تطابق.
SELECT t.transaction_id,
       p.payment_method
FROM transactions t
FULL OUTER JOIN payment_methods p
ON t.payment_method_id = p.payment_method_id;
-- استخدمنا FULL OUTER JOIN لإظهار جميع العملاء من المنطقتين حتى لو لم يوجد تطابق.
SELECT r1.customer_name AS region1_customer,
       r2.customer_name AS region2_customer
FROM region1_customers r1
FULL OUTER JOIN region2_customers r2
ON r1.customer_id = r2.customer_id;