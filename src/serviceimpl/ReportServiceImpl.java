package serviceimpl;

import dao.ReportDAO;
import daoimpl.ReportDAOImpl;
import service.ReportService;

public class ReportServiceImpl implements ReportService {

    private ReportDAO reportDAO;

    public ReportServiceImpl() {
        reportDAO = new ReportDAOImpl();
    }

    @Override
    public int getTotalPatients() {
        return reportDAO.getTotalPatients();
    }

    @Override
    public int getTotalDoctors() {
        return reportDAO.getTotalDoctors();
    }

    @Override
    public int getTotalDepartments() {
        return reportDAO.getTotalDepartments();
    }

    @Override
    public int getTotalAppointments() {
        return reportDAO.getTotalAppointments();
    }

    @Override
    public int getBookedAppointments() {
        return reportDAO.getBookedAppointments();
    }

    @Override
    public int getCancelledAppointments() {
        return reportDAO.getCancelledAppointments();
    }

    @Override
    public int getTotalBills() {
        return reportDAO.getTotalBills();
    }

    @Override
    public int getPaidBills() {
        return reportDAO.getPaidBills();
    }

    @Override
    public int getPendingBills() {
        return reportDAO.getPendingBills();
    }

    @Override
    public double getTotalRevenue() {
        return reportDAO.getTotalRevenue();
    }
}