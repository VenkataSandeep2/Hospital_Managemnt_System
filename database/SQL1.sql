
SELECT * FROM patients;

SHOW CREATE TABLE patients;

DESCRIBE doctors;

SHOW CREATE TABLE doctors;

ALTER TABLE patients
DROP INDEX phone;

INSERT INTO patients
(patient_name, age, gender, phone, address, blood_group)
VALUES
('Ravi', 25, 'Male', '9879547', 'Guntur', 'O+'),('san',26,'male','987564824','AP','O-'),
('srs',18,'female','9704259931','vjd','A+');

SELECT *
FROM patients
WHERE patient_id =15;

