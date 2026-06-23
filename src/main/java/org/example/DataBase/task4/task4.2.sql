--Q1. Use REPLACE
SELECT REPLACE('database', 'a', '@');
--Q2. Use REPLACE to replace a full word in a string
SELECT REPLACE('old laptop', 'old', 'new');
--Q3. Create a table PRODUCT with a product_name column, and
CREATE TABLE product (
    product_name VARCHAR(50)
);
INSERT INTO product VALUES ('Laptop');
INSERT INTO product VALUES ('Mouse');
INSERT INTO product VALUES ('Keyboard');
--Q4. Use LPAD to format product names
SELECT LPAD(product_name, 15, '*')
FROM product;
--Q5. Use RPAD to format product padding with '#' on the right
SELECT RPAD(product_name, 15, '#')
FROM product;