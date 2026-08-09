package app;

import service.ReportService;
import serviceimpl.ReportServiceImpl;

public class ReportMenu {

    private ReportService reportService;

    public ReportMenu() {
        reportService = new ReportServiceImpl();
    }

    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("             HOSPITAL REPORTS");
            System.out.println("========================================");
            System.out.println("1. Hospital Summary");
            System.out.println("2. Appointment Report");
            System.out.println("3. Billing Report");
            System.out.println("4. Back");
            System.out.println("========================================");

            int choice = util.InputUtil.getInt(
                    "Enter your choice: ");

            switch (choice) {

                case 1:
                    hospitalSummary();
                    break;

                case 2:
                    appointmentReport();
                    break;

                case 3:
                    billingReport();
                    break;

                case 4:
                    return;

                default:
                    System.out.println(
                            "Invalid choice.");
            }
        }
    }

    // =========================================
    // 1. HOSPITAL SUMMARY
    // =========================================
    private void hospitalSummary() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("          HOSPITAL SUMMARY");
        System.out.println("========================================");

        System.out.println(
                "Total Patients      : "
                + reportService.getTotalPatients());

        System.out.println(
                "Total Doctors       : "
                + reportService.getTotalDoctors());

        System.out.println(
                "Total Departments   : "
                + reportService.getTotalDepartments());

        System.out.println(
                "Total Appointments  : "
                + reportService.getTotalAppointments());

        System.out.println(
                "Total Bills         : "
                + reportService.getTotalBills());

        System.out.println("========================================");
    }

    // =========================================
    // 2. APPOINTMENT REPORT
    // =========================================
    private void appointmentReport() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("         APPOINTMENT REPORT");
        System.out.println("========================================");

        System.out.println(
                "Total Appointments   : "
                + reportService.getTotalAppointments());

        System.out.println(
                "Booked Appointments  : "
                + reportService.getBookedAppointments());

        System.out.println(
                "Cancelled Appointments : "
                + reportService.getCancelledAppointments());

        System.out.println("========================================");
    }

    // =========================================
    // 3. BILLING REPORT
    // =========================================
    private void billingReport() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("           BILLING REPORT");
        System.out.println("========================================");

        System.out.println(
                "Total Bills          : "
                + reportService.getTotalBills());

        System.out.println(
                "Paid Bills           : "
                + reportService.getPaidBills());

        System.out.println(
                "Pending Bills        : "
                + reportService.getPendingBills());

        System.out.printf(
                "Total Revenue        : ₹%.2f%n",
                reportService.getTotalRevenue());

        System.out.println("========================================");
    }
}