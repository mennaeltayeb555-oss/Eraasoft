-- Create a new user/role with login privilege and a password
CREATE ROLE menna WITH LOGIN PASSWORD '123';

-- Grant the ability to create tables (needs a schema-level grant too)
GRANT CREATE ON SCHEMA public TO menna;

-- Postgres doesn't have "CONNECT user/pass" as SQL —
-- you switch users via the client tool, e.g. in psql:


CREATE TABLE student (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

INSERT INTO student(id, name) VALUES (1, 'Ahmed');
SELECT * FROM student;
UPDATE student SET name = 'Ali' WHERE id = 1;
DELETE FROM student WHERE id = 1;

-- Granting privileges to another role on a specific table
GRANT INSERT, SELECT, UPDATE, DELETE ON menna.student TO hr;

-- Revoking
REVOKE ALL ON menna.student FROM hr;
REVOKE CREATE ON SCHEMA public FROM menna;