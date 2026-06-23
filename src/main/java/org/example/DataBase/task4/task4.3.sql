SELECT TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY');
--Q2. Display current date in the format 'Month YYYY
SELECT TO_CHAR(CURRENT_DATE, 'Month YYYY');
--Q3. Convert a number to character using TO_CHAR
SELECT TO_CHAR(12345.67, '99,999.99');
--Q4. Show salary formatted with a currency symbol
SELECT TO_CHAR(salary, '$99,999.99')
FROM employees;