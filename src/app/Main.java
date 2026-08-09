
package app;

import util.InputUtil;

public class Main {

    public static void main(String[] args) {

        while (true) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("      HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Department Management");
            System.out.println("4. Appointment Management");
            System.out.println("5. Billing");
            System.out.println("6. Reports");
            System.out.println("7. Exit");
            System.out.println("========================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    PatientMenu patientMenu = new PatientMenu();
                    patientMenu.showMenu();
                    break;

                case 2:
                    DoctorMenu doctorMenu = new DoctorMenu();
                    doctorMenu.showMenu();
                    break;

                case 3:
                    DepartmentMenu departmentMenu = new DepartmentMenu();
                    departmentMenu.showMenu();
                    break;

                case 4:
                    AppointmentMenu appointmentMenu =new AppointmentMenu();
                    appointmentMenu.showMenu();
                    break;
                case 5:

                    BillingMenu billingMenu = new BillingMenu();

                    billingMenu.showMenu();

                    break;
                    
                    
                case 6:
                    ReportMenu reportMenu = new ReportMenu();
                    reportMenu.showMenu();

                    break;

                case 7:
                    System.out.println(
                            "Thank you for using Hospital Management System.");
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again.");
            }
        }
    }
}

