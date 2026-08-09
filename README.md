Hospital Management System

A console-based Hospital Management System developed using Java, JDBC, and MySQL.

The application manages patients, doctors, departments, appointments, billing, payments, and hospital reports using a layered architecture.

📌 Project Overview

The Hospital Management System is designed to manage the core operations of a hospital through a menu-driven Java console application.

The system provides:

Patient Management

Doctor Management

Department Management

Appointment Management

Billing Management

Reports

MySQL database integration

Validation and exception handling

Foreign-key based data integrity

Main Application Flow

Hospital Management System
          |
          +-- Patient Management
          |
          +-- Doctor Management
          |
          +-- Department Management
          |
          +-- Appointment Management
          |
          +-- Billing
          |
          +-- Reports
          |
          +-- Exit

🚀 Features

1. Patient Management

The Patient Management module provides:

Add Patient

View All Patients

Search Patient

Update Patient

Delete Patient

Patient information includes:

Patient ID

Patient Name

Age

Gender

Phone

Address

Blood Group

2. Doctor Management

The Doctor Management module provides:

Add Doctor

View All Doctors

Search Doctor

Update Doctor

Delete Doctor

Doctor information includes:

Doctor ID

Doctor Name

Specialization

Experience

Consultation Fee

Department ID

3. Department Management

The Department Management module provides:

Add Department

View All Departments

Search Department

Update Department

Delete Department

Department names are stored as unique values in MySQL.

Example departments:

Cardiology
Neurology
Orthopedics
Pediatrics
Dermatology

4. Appointment Management

The Appointment Management module provides:

Book Appointment

View All Appointments

Search Appointment

Patient Appointment History

Doctor Schedule

Cancel Appointment

An appointment contains:

Appointment ID

Patient ID

Doctor ID

Appointment Date

Appointment Time

Status

Appointment Validation

A patient must already exist before an appointment can be booked.

A doctor must already exist before an appointment can be booked.

For example:

Enter patient ID: 50

Validation Error:
Patient not found with ID: 50

This prevents invalid foreign-key relationships.

Doctor Schedule

The Doctor Schedule displays only future appointments that are still BOOKED.

The schedule uses the equivalent condition:

status = 'BOOKED'
AND TIMESTAMP(appointment_date, appointment_time) >= NOW()

Past appointments remain in the database for history but are not displayed as active upcoming schedule entries.

💳 5. Billing Management

The Billing module provides:

Generate Bill

View All Bills

Search Bill

Search Bill by Appointment

Update Payment Status

Each bill contains:

Bill ID

Appointment ID

Consultation Fee

Medicine Charge

Lab Charge

Total Amount

Payment Status

Bill Calculation

The total bill is calculated from:

Total Amount =
Consultation Fee
+ Medicine Charge
+ Lab Charge

Example:

Consultation Fee : ₹500.00
Medicine Charge  : ₹1500.00
Lab Charge       : ₹450.00
--------------------------------
Total Amount     : ₹2450.00

New bills start with:

PENDING

The payment status can later be changed to:

PAID

Important Relationship

bill_id and patient_id are different IDs.

The relationship is:

Patient
   |
   +-- Appointment
          |
          +-- Bill

A bill is linked to an appointment using appointment_id.

📊 6. Reports

The Reports module provides:

Hospital Summary

Displays:

Total Patients

Total Doctors

Total Departments

Total Appointments

Total Bills

Appointment Report

Displays:

Total Appointments

Booked Appointments

Cancelled Appointments

Billing Report

Displays:

Total Bills

Paid Bills

Pending Bills

Total Revenue from PAID bills

🏗️ System Architecture

The project follows a layered architecture.

                    Main / Menu
                         |
                         v
                  Service Layer
                         |
                         v
                    DAO Layer
                         |
                         v
                       JDBC
                         |
                         v
                    MySQL DB

Layers

App Layer

Contains the console menus and user interaction.

src/app/

Examples:

Main.java
PatientMenu.java
DoctorMenu.java
DepartmentMenu.java
AppointmentMenu.java
BillingMenu.java
ReportMenu.java

Model Layer

Contains the Java entity classes.

src/model/

Examples:

Patient.java
Doctor.java
Department.java
Appointment.java
Bill.java

DAO Layer

Contains database operation interfaces.

src/dao/

Examples:

PatientDAO.java
DoctorDAO.java
DepartmentDAO.java
AppointmentDAO.java
BillDAO.java
ReportDAO.java

DAO Implementation Layer

Contains JDBC and SQL implementations.

src/daoimpl/

Examples:

PatientDAOImpl.java
DoctorDAOImpl.java
DepartmentDAOImpl.java
AppointmentDAOImpl.java
BillDAOImpl.java
ReportDAOImpl.java

Service Layer

Contains service interfaces.

src/service/

Service Implementation Layer

Contains service implementations.

src/serviceimpl/

Validation Layer

Contains validation classes.

src/validation/

Exception Layer

Contains custom exceptions.

src/exception/

Utility Layer

Contains common utilities.

src/util/

Examples:

DBConnection.java
InputUtil.java

🗄️ Database Design

Database name:

hospital_db

Main tables:

departments
doctors
patients
appointments
bills

Relationship Diagram

departments
     |
     | 1
     |
     | N
   doctors
     |
     | 1
     |
     | N
appointments
   /       \
  /         \
 N           N
patients     bills

More specifically:

departments.department_id
        |
        +---- doctors.department_id

patients.patient_id
        |
        +---- appointments.patient_id

doctors.doctor_id
        |
        +---- appointments.doctor_id

appointments.appointment_id
        |
        +---- bills.appointment_id

🗃️ Database Tables

departments

department_id
department_name

department_id is the primary key.

department_name is unique.

doctors

doctor_id
doctor_name
specialization
experience
consultation_fee
department_id

doctor_id is the primary key.

department_id references departments.

patients

patient_id
patient_name
age
gender
phone
address
blood_group

patient_id is the primary key.

appointments

appointment_id
patient_id
doctor_id
appointment_date
appointment_time
status

appointment_id is the primary key.

patient_id references patients.

doctor_id references doctors.

Default appointment status:

BOOKED

bills

bill_id
appointment_id
consultation_fee
medicine_charge
lab_charge
total_amount
payment_status

bill_id is the primary key.

appointment_id references appointments.

Default payment status:

PENDING

🛠️ Technologies Used

Technology

Purpose

Java

Application development

JDBC

Database connectivity

MySQL

Relational database

Eclipse

Development environment

Git

Version control

GitHub

Source code hosting

📁 Project Structure

HospitalManagementSystem/
│
├── database/
│   └── hospital_db.sql
│
├── src/
│   ├── app/
│   │   ├── Main.java
│   │   ├── PatientMenu.java
│   │   ├── DoctorMenu.java
│   │   ├── DepartmentMenu.java
│   │   ├── AppointmentMenu.java
│   │   ├── BillingMenu.java
│   │   └── ReportMenu.java
│   │
│   ├── dao/
│   ├── daoimpl/
│   ├── exception/
│   ├── model/
│   ├── service/
│   ├── serviceimpl/
│   ├── util/
│   └── validation/
│
├── .gitignore
└── README.md

⚙️ Prerequisites

Before running the project, install:

Java JDK

MySQL Server

MySQL Workbench (recommended)

Eclipse IDE

MySQL Connector/J

Git (optional, for source control)

🔧 Database Setup

Step 1 — Start MySQL

Make sure MySQL Server is running.

Step 2 — Open MySQL Workbench

Open the file:

database/hospital_db.sql

Step 3 — Create the Database

Run the SQL script.

The script creates:

hospital_db

and the required tables:

departments
doctors
patients
appointments
bills

Step 4 — Verify the Database

Run:

USE hospital_db;

SHOW TABLES;

You should see:

appointments
bills
departments
doctors
patients

🔌 Configure Database Connection

Open:

src/util/DBConnection.java

Update your local MySQL credentials.

Example:

private static final String URL =
        "jdbc:mysql://localhost:3306/hospital_db";

private static final String USERNAME = "root";

private static final String PASSWORD =
        "YOUR_MYSQL_PASSWORD";

Do not commit your real database password to GitHub.

▶️ How to Run the Project

Using Eclipse

Open Eclipse.

Import/open the project.

Make sure MySQL is running.

Make sure the database has been created.

Verify DBConnection.java.

Make sure MySQL Connector/J is available in the project.

Open:

src/app/Main.java

Run Main.java.

The application will display:

========================================
       HOSPITAL MANAGEMENT SYSTEM
========================================

1. Patient Management
2. Doctor Management
3. Department Management
4. Appointment Management
5. Billing
6. Reports
7. Exit

🧪 Example Workflow

A typical hospital workflow is:

1. Register Patient
        |
        v
2. Add / Select Doctor
        |
        v
3. Select Department
        |
        v
4. Book Appointment
        |
        v
5. Generate Bill
        |
        v
6. Update Payment
        |
        v
7. View Reports

For a new patient, the current implementation uses the Patient Management module first. After the patient is registered, the generated patient ID can be used for appointment booking.

🔐 Validation and Error Handling

The project includes validation and exception handling.

Examples include:

Invalid Patient

Validation Error:
Patient not found with ID: 50

Duplicate Department

Duplicate entry for department name

The database also protects relationships using foreign keys.

🧪 Tested Scenarios

The application has been tested for:

Patient registration

Patient search

Patient update

Doctor creation

Doctor search

Doctor update

Doctor deletion

Department creation

Duplicate department validation

Appointment booking

Invalid patient validation

Doctor schedule

Past appointment filtering

Appointment search

Appointment cancellation

Bill generation

Bill search

Payment status update

Hospital summary

Appointment report

Billing report

📈 Example Report

========================================
          HOSPITAL SUMMARY
========================================

Total Patients      : 12
Total Doctors       : 5
Total Departments   : 6
Total Appointments  : 5
Total Bills         : 2

========================================

💡 Key Project Highlights

Layered Java architecture

Object-oriented programming

JDBC and MySQL integration

CRUD operations

PreparedStatement-based database operations

Foreign-key relationships

Input validation

Custom exceptions

Appointment validation

Future doctor schedule filtering

Automatic bill calculation

Payment status tracking

Database-driven reports

🚀 Future Enhancements

The following features can be added in a future version:

New patient registration directly from appointment booking

Department selection by name

Doctor selection by name

Automatic transition from BOOKED to COMPLETED

Authentication and login

Role-based access for admin, receptionist, doctor, and billing staff

Graphical user interface

Spring Boot REST API

Web frontend

Online appointment booking

Email/SMS appointment notifications

Advanced reports and dashboards

Unit and integration testing

📚 Learning Outcomes

This project demonstrates practical knowledge of:

Core Java

Object-Oriented Programming

Interfaces

Exception Handling

Collections

JDBC

SQL

MySQL

CRUD operations

Database relationships

Foreign keys

PreparedStatement

ResultSet

Layered architecture

Git and GitHub

👨‍💻 Author

Venkata Sandeep

Hospital Management SystemJava | JDBC | MySQL

📄 Documentation

Detailed project documentation is maintained separately with information about:

Project architecture

Database design

Module functionality

Business rules

Testing

Future enhancements

📌 Project Status

Core project: Completed ✅

The current version contains the main hospital management modules and database integration required for the console-based application.
