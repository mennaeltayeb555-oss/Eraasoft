CREATE TABLE emp_test (
    name VARCHAR(50)
);
insert into emp_test values ('     ALI     ');
insert into emp_test values ('     AHMED   ');
insert into emp_test values ('    MENNA    ');

SELECT TRIM(name)
FROM emp_test;

--Q3. Use LTRIM to remove only leading spaces
select LTRIM(NAME)
from emp_test;
--Q4. Use RTRIM to remove only trailing spaces
select rtrim(NAME)
from emp_test;
--Q5. Use TRIM to remove a specific character
select TRIM(both '*'from '***ALI***');
SELECT TRIM(BOTH '#' FROM '###Menna###');