--استخدمنا ANY لأن الـ Subquery ترجع أكثر من مرتب ونريد المقارنة مع أي واحد منهم
SELECT first_name,
       last_name,
       salary
FROM employees
WHERE salary > ANY (
    SELECT salary
    FROM employees
    WHERE department_id = 10
);
--استخدمنا ALL لأن المرتب يجب أن يكون أقل من جميع مرتبات القسم 20
SELECT first_name,
       last_name,
       salary
FROM employees
WHERE salary < ALL (
    SELECT salary
    FROM employees
    WHERE department_id = 20
);
ا-- IN لأن الـ Subquery ترجع أكثر من سعر
SELECT product_name,
       price
FROM products
WHERE price IN (
    SELECT price
    FROM products
    WHERE category = 'Electronics'
);
--استخدمنا IN للحصول على العملاء الذين طلبوا منتجات سعرها أكبر من 1000
SELECT customer_name
FROM customers
WHERE customer_id IN (
    SELECT customer_id
    FROM orders
    WHERE product_id IN (
        SELECT product_id
        FROM products
        WHERE price > 1000
    )
);
--استخدمنا IN للحصول على الوظائف التي تتكرر أكثر من مرة
SELECT first_name,
       last_name,
       job_title
FROM employees
WHERE job_title IN (
    SELECT job_title
    FROM employees
    GROUP BY job_title
    HAVING COUNT(*) > 1
);
--استخدمنا GROUP BY و HAVING لإرجاع الأقسام التي تحتوي على أكثر من موظف
SELECT department_name
FROM departments
WHERE department_id IN (
    SELECT department_id
    FROM employees
    GROUP BY department_id
    HAVING COUNT(*) > 1
);
--استخدمنا IN للحصول على العملاء الذين ينتمون لمدن يوجد بها عملاء آخرون لديهم طلبات
SELECT *
FROM orders
WHERE customer_id IN (
    SELECT customer_id
    FROM customers
    WHERE city IN (
        SELECT city
        FROM customers
        GROUP BY city
        HAVING COUNT(*) > 1
    )
);
--استخدمنا IN للحصول على المؤلفين الذين لديهم أكثر من كتاب
SELECT book_title
FROM books
WHERE author_id IN (
    SELECT author_id
    FROM books
    GROUP BY author_id
    HAVING COUNT(*) > 1
);
--ستخدمنا IN للحصول على جميع الكورسات التي يدرسها Dr. Smith ثم عرض الطلاب المسجلين بها.
SELECT student_name
FROM students
WHERE student_id IN (
    SELECT student_id
    FROM enrollments
    WHERE course_id IN (
        SELECT course_id
        FROM courses
        WHERE professor_name = 'Dr. Smith'
    )
);
--استخدمنا IN لأن الـ Subquery ترجع أكثر من مرتب
SELECT first_name,
       last_name,
       salary
FROM employees
WHERE salary IN (
    SELECT salary
    FROM employees
    WHERE department_id = 30
);

