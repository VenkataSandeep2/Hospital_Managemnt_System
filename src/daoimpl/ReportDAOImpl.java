package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ReportDAO;
import util.DBConnection;

public class ReportDAOImpl implements ReportDAO {

    @Override
    public int getTotalPatients() {

        String sql = "SELECT COUNT(*) FROM patients";

        return getCount(sql);
    }

    @Override
    public int getTotalDoctors() {

        String sql = "SELECT COUNT(*) FROM doctors";

        return getCount(sql);
    }

    @Override
    public int getTotalDepartments() {

        String sql = "SELECT COUNT(*) FROM departments";

        return getCount(sql);
    }

    @Override
    public int getTotalAppointments() {

        String sql = "SELECT COUNT(*) FROM appointments";

        return getCount(sql);
    }

    @Override
    public int getBookedAppointments() {

        String sql =
                "SELECT COUNT(*) FROM appointments "
              + "WHERE status = 'BOOKED'";

        return getCount(sql);
    }

    @Override
    public int getCancelledAppointments() {

        String sql =
                "SELECT COUNT(*) FROM appointments "
              + "WHERE status = 'CANCELLED'";

        return getCount(sql);
    }

    @Override
    public int getTotalBills() {

        String sql = "SELECT COUNT(*) FROM bills";

        return getCount(sql);
    }

    @Override
    public int getPaidBills() {

        String sql =
                "SELECT COUNT(*) FROM bills "
              + "WHERE payment_status = 'PAID'";

        return getCount(sql);
    }

    @Override
    public int getPendingBills() {

        String sql =
                "SELECT COUNT(*) FROM bills "
              + "WHERE payment_status = 'PENDING'";

        return getCount(sql);
    }

    @Override
    public double getTotalRevenue() {

        String sql =
                "SELECT COALESCE(SUM(total_amount), 0) "
              + "FROM bills "
              + "WHERE payment_status = 'PAID'";

        try (Connection connection =
                     DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     preparedStatement.executeQuery()) {

            if (resultSet.next()) {

                return resultSet.getDouble(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0.0;
    }

    // =========================================
    // COMMON COUNT METHOD
    // =========================================
    private int getCount(String sql) {

        try (Connection connection =
                     DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     preparedStatement.executeQuery()) {

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }
}