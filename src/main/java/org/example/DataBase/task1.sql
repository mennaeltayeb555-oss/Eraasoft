CREATE TABLE Manager (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    birth_date DATE,
    address VARCHAR(255)
);

ALTER TABLE Manager DROP COLUMN address;

ALTER TABLE Manager ADD COLUMN city_address VARCHAR(100);
ALTER TABLE Manager ADD COLUMN street VARCHAR(100);

ALTER TABLE Manager RENAME COLUMN name TO full_name;

REVOKE INSERT, UPDATE, DELETE, TRUNCATE ON Manager FROM PUBLIC;

CREATE TABLE Owner AS 
SELECT id, full_name, birth_date 
FROM Manager;

ALTER TABLE Manager RENAME TO Master;

DROP TABLE IF EXISTS Master;
DROP TABLE IF EXISTS Owner;