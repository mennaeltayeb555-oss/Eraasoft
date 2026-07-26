--الربط عن طريق العمود country_id
--natural join beacuse its the same name of the column
SELECT *
FROM locations
NATURAL JOIN countries;
--استخدمنا USING لأن اسم العمود متطابق في الجدولين وهو COUNTRY_ID
SELECT *
FROM locations
JOIN countries
USING (country_id);
--we use on to add constrain
SELECT *
FROM locations l
JOIN countries c
ON l.country_id = c.country_id;
--inner to show the matched rows only 
SELECT *
FROM locations l
INNER JOIN countries c
ON l.country_id = c.country_id;
--استخدمنا LEFT JOIN لإظهار جميع الـ Locations حتى لو لم يوجد Country مطابق
SELECT *
FROM locations l
LEFT JOIN countries c
ON l.country_id = c.country_id;
--استخدمنا RIGHT JOIN لإظهار جميع الـ Countries حتى لو لم يوجد Location مرتبط بها.
SELECT *
FROM locations l
RIGHT JOIN countries c
ON l.country_id = c.country_id;
--استخدمنا FULL OUTER JOIN لإظهار جميع الصفوف من الجدولين سواء وجد تطابق أم لا
SELECT *
FROM locations l
FULL OUTER JOIN countries c
ON l.country_id = c.country_id;