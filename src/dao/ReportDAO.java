package dao;

public interface ReportDAO {

    int getTotalPatients();

    int getTotalDoctors();

    int getTotalDepartments();

    int getTotalAppointments();

    int getBookedAppointments();

    int getCancelledAppointments();

    int getTotalBills();

    int getPaidBills();

    int getPendingBills();

    double getTotalRevenue();
}