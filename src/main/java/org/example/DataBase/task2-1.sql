-- ========================================
-- Database Task - Doctor Table Operations
-- ========================================

-- 1. Create Table Doctor
DROP TABLE IF EXISTS Doctor;
CREATE TABLE Doctor (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    salary NUMERIC(10,2),
    address VARCHAR(255)
);

-- 2. Insert 10 Rows
INSERT INTO Doctor (name, salary, address) VALUES
('Ahmed Hassan', 15000, 'Cairo, Nasr City'),
('Mona Ali',     12000, 'Alexandria, Smouha'),
('Omar Khaled',  18000, 'Giza, Mohandessin'),
('Nada Mahmoud', 14000, 'Mansoura, Torail'),
('Khaled Samy',  22000, 'Tanta, El-Geish Street'),
('Sara Ahmed',   11000, 'Aswan, Kornish El-Nile'),
('Ali Youssef',  17000, 'Luxor, El-Mahatta'),
('Heba Tarek',   13000, 'Port Said, El-Gomhoreya'),
('Youssef Emad', 16000, 'Ismailia, El-Sheikh Zayed'),
('Dina Osama',   19000, 'Suez, El-Salam');

-- 3. Update Record 3 Salary to 20000
UPDATE Doctor SET salary = 20000 WHERE id = 3;

-- 4. Delete Record Number 9
DELETE FROM Doctor WHERE id = 9;

-- 5. Concatenation (Name + Salary)
SELECT name || ' earns ' || salary || ' EGP' AS "Doctor Salary Info"
FROM Doctor;

-- 6. Display Salary * 2
SELECT id, name, salary, salary * 2 AS "Doubled Salary", address
FROM Doctor;

-- 7. Select with Salary IN (1000, 2000, 3000)
SELECT * FROM Doctor WHERE salary IN (1000, 2000, 3000);

-- 8. Rename Table to PRD_DOCTOR
ALTER TABLE Doctor RENAME TO PRD_DOCTOR;

-- Show Final Table
SELECT * FROM PRD_DOCTOR;