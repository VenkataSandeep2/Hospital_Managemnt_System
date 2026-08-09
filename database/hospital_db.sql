create database Hospital_db;

show databases;

use hospital_db;

CREATE TABLE departments (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE doctors (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    doctor_name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    experience INT NOT NULL,
    consultation_fee DECIMAL(10,2) NOT NULL,
    department_id INT,
    FOREIGN KEY (department_id)
        REFERENCES departments(department_id)
);

create table patients(
	patient_id int primary key auto_increment,
    patient_name varchar(100) not null,
    age int not null,
	gender varchar(50) not null,
    phone varchar(15) ,
    address varchar(155),
    blood_group varchar(25)
);

create table appointments (
	appointment_id int primary key auto_increment,
	patient_id int not null,
	doctor_id int not null,
	appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status VARCHAR(20) DEFAULT 'BOOKED', 
    FOREIGN KEY (patient_id)
    REFERENCES patients(patient_id),
		FOREIGN KEY (doctor_id)
		REFERENCES doctors(doctor_id)
);


CREATE TABLE bills (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id INT NOT NULL,
    consultation_fee DECIMAL(10,2) NOT NULL,
    medicine_charge DECIMAL(10,2) DEFAULT 0,
    lab_charge DECIMAL(10,2) DEFAULT 0,
    total_amount DECIMAL(10,2) NOT NULL,
    payment_status VARCHAR(20) DEFAULT 'PENDING',
    FOREIGN KEY (appointment_id)
        REFERENCES appointments(appointment_id)
);


show tables;

drop table bills;


SELECT * FROM patients;

SHOW CREATE TABLE patients;

DESCRIBE doctors;

SHOW CREATE TABLE doctors;

ALTER TABLE patients
DROP INDEX phone;

INSERT INTO patients
(patient_name, age, gender, phone, address, blood_group)
VALUES
('Ravi', 25, 'Male', '9879547', 'Guntur', 'O+'),
('San', 26, 'Male', '987564824', 'AP', 'O-'),
('SRS', 18, 'Female', '9704259931', 'Vijayawada', 'A+');
SELECT *
FROM patients
WHERE patient_id =15;
select * from doctors;

delete from doctors where doctor_id = 2;

DESCRIBE departments;

SHOW CREATE TABLE departments;



SELECT * FROM doctors WHERE doctor_id = 4;

DESCRIBE appointments;

SHOW CREATE TABLE appointments;	

SELECT doctor_id, doctor_name
FROM doctors
ORDER BY doctor_id;

SELECT patient_id, patient_name
FROM patients
ORDER BY patient_id;

SELECT * FROM appointments;

otal_amount), 0)
FROM bills
WHERE payment_status = 'PAID';

SELECT *
FROM appointments
WHERE doctor_id = ?
  AND status = 'BOOKED'
  AND TIMESTAMP(appointment_date, appointment_time) >= NOW()
ORDER BY appointment_date, appointment_time;