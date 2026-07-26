-حتي لو مفيش موظفين القسم لازم يظهر 
SELECT e.employee_id,
       e.first_name,
       d.department_name
FROM employees e
RIGHT JOIN departments d
ON e.department_id = d.department_id;
--كل العملاء لازم يظهروا حتي لو مفيش ليهم اوردرات
SELECT o.order_id,
       o.order_date,
       c.customer_id,
       c.customer_name
FROM orders o
RIGHT JOIN customers c
ON o.customer_id = c.customer_id;
-- الكورسات لازم تظهر حتي لو مش مسجل فيها اي طالب
SELECT s.student_id,
       s.student_name,
       c.course_name
FROM students s
RIGHT JOIN courses c
ON s.course_id = c.course_id;
--استخدمنا RIGHT JOIN لإظهار جميع المشاريع حتى لو لم يتم تعيين أي موظف عليها.
SELECT e.employee_id,
       e.first_name,
       p.project_name
FROM employees e
RIGHT JOIN projects p
ON e.project_id = p.project_id;
-- استخدمنا RIGHT JOIN لإظهار جميع طرق الدفع حتى لو لم توجد معاملات مرتبطة بها.
SELECT t.transaction_id,
       t.amount,
       p.payment_method
FROM transactions t
RIGHT JOIN payment_methods p
ON t.payment_method_id = p.payment_method_id;
-- استخدمنا RIGHT JOIN لإظهار جميع المؤلفين حتى لو لم يؤلفوا أي كتاب.
SELECT b.book_title,
       a.author_name
FROM books b
RIGHT JOIN authors a
ON b.author_id = a.author_id;
-- استخدمنا RIGHT JOIN لإظهار جميع التصنيفات حتى لو لم تحتوي على أي منتجات.
SELECT p.product_name,
       c.category_name
FROM products p
RIGHT JOIN categories c
ON p.category_id = c.category_id;

