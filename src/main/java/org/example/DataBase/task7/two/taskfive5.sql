--استخدمنا Subquery للحصول على أعلى مرتب ثم قارنا به
SELECT first_name,
       last_name,
       salary
from employees
where salary = (
      select MAX(salary)
      from employees

);
--استخدمنا Subquery للحصول على رقم قسم Alice ثم بحثنا عن الموظفين في نفس القسم
SELECT first_name,
       last_name
FROM employees
WHERE department_id = (
    SELECT department_id
    FROM employees
    WHERE first_name = 'Alice'
);
--للحصول على أقل سعر ثم عرضنا المنتج
SELECT *
FROM products
WHERE price = (
    SELECT MIN(price)
    FROM products
);
ا-- Nested Subquery للحصول على أعلى مرتب ثم رقم القسم ثم اسم القسم
SELECT department_name
FROM departments
WHERE department_id = (
    SELECT department_id --quary inside quary
    FROM employees
    WHERE salary = (
        SELECT MAX(salary)
        FROM employees
    )
);
--استخدمنا Subquery للحصول على أحدث تاريخ تعيين ثم عرض المدير.
SELECT manager_id
FROM employees
WHERE hire_date = (
    SELECT MAX(hire_date)
    FROM employees
);
--استخدمنا Subquery لحساب متوسط المرتبات ثم قارنا به
--salary must = avarge to return it
SELECT first_name,
       last_name,
       salary
FROM employees
WHERE salary = (
    SELECT AVG(salary)
    FROM employees
);
--للحصول على أقدم تاريخ طلب
SELECT *
FROM orders
WHERE order_date = (
    SELECT MIN(order_date)
    FROM orders
);
--لحصول على مرتب الموظف 101 ثم قارنا به
SELECT first_name,
       last_name,
       salary
FROM employees
WHERE salary > (
    SELECT salary
    FROM employees
    WHERE employee_id = 101
);
--لحصول على GPA الخاص بـ John Doe ثم بحثنا عن نفس الـ GPA
SELECT student_name,
       gpa
FROM students
WHERE gpa = (
    SELECT gpa
    FROM students
    WHERE student_name = 'John Doe'
);
--استخدمنا Subquery للحصول على أعلى سعر في قسم Science ثم قارنا به.
SELECT book_title,
       price
FROM books
WHERE price = (
    SELECT MAX(price)
    FROM books
    WHERE category = 'Science'
);

 
      