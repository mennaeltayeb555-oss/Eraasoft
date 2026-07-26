--لحساب متوسط المرتبات ثم قارنا به
SELECT first_name,
       last_name,
       salary
FROM employees
WHERE salary > (
    SELECT AVG(salary)
    FROM employees
);
--حساب أكبر عدد طلبات ثم قارنا به
SELECT customer_id,
       COUNT(*) AS total_orders
FROM orders
GROUP BY customer_id
HAVING COUNT(*) = (
    SELECT MAX(order_count)
    FROM (
        SELECT COUNT(*) AS order_count
        FROM orders
        GROUP BY customer_id
    ) t
);
--لمقارنة السعر مع أسعار منتجات قسم
SELECT product_name,
       price
FROM products
WHERE price > ANY (
    SELECT price
    FROM products
    WHERE category = 'Accessories'
);
SELECT first_name,
       last_name
FROM employees
WHERE department_id = (
    SELECT department_id
    FROM employees
    WHERE first_name = 'John'
      AND last_name = 'Smith'
);
--who is the customer placed in newyork
SELECT *
FROM orders
WHERE customer_id IN (
    SELECT customer_id
    FROM customers
    WHERE city = 'New York'
);
--ستخدمنا NOT EXISTS لإظهار الأقسام التي لا تحتوي على موظفين
SELECT department_id,
       department_name
FROM departments d
WHERE NOT EXISTS (
    SELECT 1
    FROM employees e
    WHERE e.department_id = d.department_id
);
ا--ا NOT IN لإظهار الطلاب غير المسجلين في أي Course.
SELECT *
FROM students
WHERE student_id NOT IN (
    SELECT student_id
    FROM enrollments
);
--للحصول على أكبر مرتب ثم استبعدناه.
SELECT MAX(salary) AS second_highest_salary
FROM employees
WHERE salary < (
    SELECT MAX(salary)
    FROM employees
);
-- Scalar Subquery لحساب متوسط الأسعار ثم قارنا به
SELECT product_name,
       price
FROM products
WHERE price > (
    SELECT AVG(price)
    FROM products
);
--to make sure that customer ordered from products 'A'
SELECT c.customer_id,
       c.customer_name
FROM customers c
WHERE NOT EXISTS (
    SELECT p.product_id
    FROM products p
    WHERE p.category = 'A'
      AND NOT EXISTS (
          SELECT 1
          FROM orders o
          WHERE o.customer_id = c.customer_id
            AND o.product_id = p.product_id
      )
);