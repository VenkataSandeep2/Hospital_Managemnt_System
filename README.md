# Hospital Management System

A console-based **Hospital Management System** developed using **Java, JDBC, and MySQL**.

This project manages the main operations of a hospital, including patients, doctors, departments, appointments, billing, payments, and reports.

---

## 📌 Project Overview

The Hospital Management System is a menu-driven Java application connected to a MySQL database using JDBC.

### Main Modules

1. Patient Management
2. Doctor Management
3. Department Management
4. Appointment Management
5. Billing
6. Reports

### Application Flow

```text
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
```

---

# 🚀 Features

## 1. Patient Management

* Add Patient
* View All Patients
* Search Patient
* Update Patient
* Delete Patient

Patient information includes:

* Patient ID
* Patient Name
* Age
* Gender
* Phone
* Address
* Blood Group

---

## 2. Doctor Management

* Add Doctor
* View All Doctors
* Search Doctor
* Update Doctor
* Delete Doctor

Doctor information includes:

* Doctor ID
* Doctor Name
* Specialization
* Experience
* Consultation Fee
* Department ID

---

## 3. Department Management

* Add Department
* View All Departments
* Search Department
* Update Department
* Delete Department

Department names are unique.

Example departments:

```text
Cardiology
Neurology
Orthopedics
Pediatrics
Dermatology
```

---

## 4. Appointment Management

* Book Appointment
* View All Appointments
* Search Appointment
* Patient Appointment History
* Doctor Schedule
* Cancel Appointment

Appointment information includes:

* Appointment ID
* Patient ID
* Doctor ID
* Appointment Date
* Appointment Time
* Status

### Appointment Validation

A patient must already exist before booking an appointment.

A doctor must already exist before booking an appointment.

Example:

```text
Enter patient ID: 50

Validation Error:
Patient not found with ID: 50
```

### Doctor Schedule

The doctor schedule displays only future appointments that are still `BOOKED`.

Past appointments remain in the database for history but are not shown as upcoming appointments.

---

# 💳 Billing Management

The Billing module provides:

* Generate Bill
* View All Bills
* Search Bill
* Search Bill by Appointment
* Update Payment Status

Each bill contains:

* Bill ID
* Appointment ID
* Consultation Fee
* Medicine Charge
* Lab Charge
* Total Amount
* Payment Status

### Bill Calculation

```text
Total Amount =
Consultation Fee
+ Medicine Charge
+ Lab Charge
```

Example:

```text
Consultation Fee : ₹500.00
Medicine Charge  : ₹1500.00
Lab Charge       : ₹450.00
--------------------------------
Total Amount     : ₹2450.00
```

New bills start with:

```text
PENDING
```

The payment status can later be changed to:

```text
PAID
```

### Important

`bill_id` and `patient_id` are different IDs.

The relationship is:

```text
Patient
   |
   +-- Appointment
          |
          +-- Bill
```

A bill is linked to an appointment using `appointment_id`.

---

# 📊 Reports

The Reports module provides three reports.

## Hospital Summary

Displays:

* Total Patients
* Total Doctors
* Total Departments
* Total Appointments
* Total Bills

## Appointment Report

Displays:

* Total Appointments
* Booked Appointments
* Cancelled Appointments

## Billing Report

Displays:

* Total Bills
* Paid Bills
* Pending Bills
* Total Revenue from PAID bills

---

# 🏗️ System Architecture

The project follows a layered architecture.

```text
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
                MySQL Database
```

### Project Layers

#### App Layer

Contains menus and user interaction.

```text
src/app/
```

Examples:

```text
Main.java
PatientMenu.java
DoctorMenu.java
DepartmentMenu.java
AppointmentMenu.java
BillingMenu.java
ReportMenu.java
```

#### Model Layer

Contains entity classes.

```text
src/model/
```

Examples:

```text
Patient.java
Doctor.java
Department.java
Appointment.java
Bill.java
```

#### DAO Layer

Contains database operation interfaces.

```text
src/dao/
```

#### DAO Implementation Layer

Contains JDBC and SQL implementations.

```text
src/daoimpl/
```

#### Service Layer

Contains service interfaces.

```text
src/service/
```

#### Service Implementation Layer

Contains business/service implementations.

```text
src/serviceimpl/
```

#### Validation Layer

Contains validation classes.

```text
src/validation/
```

#### Exception Layer

Contains custom exceptions.

```text
src/exception/
```

#### Utility Layer

Contains common utilities.

```text
src/util/
```

Examples:

```text
DBConnection.java
InputUtil.java
```

---

# 🗄️ Database Design

Database name:

```text
hospital_db
```

Main tables:

```text
departments
doctors
patients
appointments
bills
```

## Database Relationships

```text
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
    /      \
   /        \
patients    bills
```

More specifically:

```text
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
```

---

# 🗃️ Database Tables

## departments

```text
department_id
department_name
```

* `department_id` → Primary Key
* `department_name` → Unique

## doctors

```text
doctor_id
doctor_name
specialization
experience
consultation_fee
department_id
```

* `doctor_id` → Primary Key
* `department_id` → Foreign Key

## patients

```text
patient_id
patient_name
age
gender
phone
address
blood_group
```

* `patient_id` → Primary Key

## appointments

```text
appointment_id
patient_id
doctor_id
appointment_date
appointment_time
status
```

* `appointment_id` → Primary Key
* `patient_id` → Foreign Key
* `doctor_id` → Foreign Key
* Default status → `BOOKED`

## bills

```text
bill_id
appointment_id
consultation_fee
medicine_charge
lab_charge
total_amount
payment_status
```

* `bill_id` → Primary Key
* `appointment_id` → Foreign Key
* Default payment status → `PENDING`

---

# 🛠️ Technologies Used

| Technology | Purpose                 |
| ---------- | ----------------------- |
| Java       | Application development |
| JDBC       | Database connectivity   |
| MySQL      | Relational database     |
| Eclipse    | Development environment |
| Git        | Version control         |
| GitHub     | Source code hosting     |

---

# 📁 Project Structure

```text
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
```

---

# ⚙️ Prerequisites

Install the following before running the project:

1. Java JDK
2. MySQL Server
3. MySQL Workbench
4. Eclipse IDE
5. MySQL Connector/J
6. Git (optional)

---

# 🔧 Database Setup

### Step 1 — Start MySQL

Make sure MySQL Server is running.

### Step 2 — Open MySQL Workbench

Open:

```text
database/hospital_db.sql
```

### Step 3 — Run the SQL Script

The script creates:

```text
hospital_db
```

and the required tables:

```text
departments
doctors
patients
appointments
bills
```

### Step 4 — Verify

Run:

```sql
USE hospital_db;

SHOW TABLES;
```

You should see:

```text
appointments
bills
departments
doctors
patients
```

---

# 🔌 Configure Database Connection

Open:

```text
src/util/DBConnection.java
```

Update your local MySQL credentials.

Example:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/hospital_db";

private static final String USERNAME = "root";

private static final String PASSWORD =
        "YOUR_MYSQL_PASSWORD";
```

**Do not upload your real MySQL password to GitHub.**

---

# ▶️ How to Run

### Using Eclipse

1. Open Eclipse.
2. Import the Hospital Management System project.
3. Start MySQL Server.
4. Create the `hospital_db` database.
5. Run `database/hospital_db.sql`.
6. Configure `DBConnection.java`.
7. Make sure MySQL Connector/J is available.
8. Open:

```text
src/app/Main.java
```

9. Right-click `Main.java`.
10. Select **Run As → Java Application**.

The application will display:

```text
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
```

---

# 🧪 Example Workflow

```text
Register Patient
       ↓
Select/Add Doctor
       ↓
Select Department
       ↓
Book Appointment
       ↓
Generate Bill
       ↓
Update Payment
       ↓
View Reports
```

For a new patient:

```text
Patient Management
       ↓
Add Patient
       ↓
Get Patient ID
       ↓
Appointment Management
       ↓
Book Appointment
```

---

# 🔐 Validation and Exception Handling

The application uses validation, custom exceptions, JDBC exception handling, and database constraints.

Examples:

### Invalid Patient

```text
Validation Error:
Patient not found with ID: 50
```

### Duplicate Department

```text
Duplicate entry for department name
```

### Foreign Key Validation

An appointment cannot be created for a patient or doctor that does not exist.

---

# 🧪 Tested Scenarios

The project has been tested for:

* Patient registration
* Patient search
* Patient update
* Doctor creation
* Doctor search
* Doctor update
* Doctor deletion
* Department creation
* Duplicate department validation
* Appointment booking
* Invalid patient validation
* Doctor schedule
* Past appointment filtering
* Appointment search
* Appointment cancellation
* Bill generation
* Bill search
* Payment status update
* Hospital summary
* Appointment report
* Billing report

---

# 💡 Key Project Highlights

* Layered architecture
* Object-Oriented Programming
* Java JDBC integration
* MySQL database
* CRUD operations
* PreparedStatement
* ResultSet
* Foreign-key relationships
* Input validation
* Custom exceptions
* Appointment validation
* Future doctor schedule filtering
* Automatic bill calculation
* Payment status tracking
* Database-driven reports

---

# 🚀 Future Enhancements

Possible future improvements:

* Register a new patient directly during appointment booking
* Select doctors by name instead of ID
* Select departments by name instead of ID
* Automatically mark completed appointments
* Login and authentication
* Role-based access
* Admin dashboard
* Doctor dashboard
* Receptionist dashboard
* Web-based user interface
* Spring Boot REST API
* Online appointment booking
* Email/SMS notifications
* Advanced reports and dashboards
* Unit testing and integration testing

---

# 📚 Learning Outcomes

This project demonstrates practical knowledge of:

* Core Java
* Object-Oriented Programming
* Interfaces
* Exception Handling
* Collections
* JDBC
* SQL
* MySQL
* CRUD Operations
* Foreign Keys
* Database Relationships
* PreparedStatement
* ResultSet
* Layered Architecture
* Git
* GitHub

---

# 👨‍💻 Author

**Venkata Sandeep**

**Hospital Management System**

Java | JDBC | MySQL

---

# 📌 Project Status

**Core Project: Completed ✅**

The project currently contains the main hospital management modules with MySQL database integration, validation, exception handling, billing, appointment management, and reporting.
