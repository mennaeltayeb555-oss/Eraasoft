CREATE TABLE Player (
    id INT NOT NULL UNIQUE,
    name VARCHAR(100) UNIQUE,
    age INT
);
--Requirements:

--id, name, salary
--id → NOT NULL
--id and name → UNIQUE together (Composite Unique Constraint)
CREATE TABLE Manager (
    id INT NOT NULL,
    name VARCHAR(100),
    salary DECIMAL(10,2),
    UNIQUE (id, name)
);
--Requirements:

--id, name, age
--id → NOT NULL
--id → UNIQUE (Primary Key)
CREATE TABLE Manager (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT
);
