package daoimpl;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BillDAO;
import model.Bill;
import util.DBConnection;

public class BillDAOImpl implements BillDAO 
{
	// 1. ADD BILL
    // =========================================
    @Override
    public boolean addBill(Bill bill) 
    {

    	String sql = "INSERT INTO bills "
                   + "(appointment_id, consultation_fee, "
                   + "medicine_charge, lab_charge, "
                   + "total_amount, payment_status) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, bill.getAppointmentId());

            preparedStatement.setBigDecimal(2, bill.getConsultationFee());

            preparedStatement.setBigDecimal(3, bill.getMedicineCharge());

            preparedStatement.setBigDecimal(4, bill.getLabCharge());

            preparedStatement.setBigDecimal(5, bill.getTotalAmount());

            preparedStatement.setString(6, bill.getPaymentStatus());

            int rowsAffected =preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) 
        {

            e.printStackTrace();
        }

        return false;
    }

    // 2. GET ALL BILLS
    // =========================================
    @Override
    public List<Bill> getAllBills() 
    {

        List<Bill> bills = new ArrayList<>();

        String sql ="SELECT * FROM bills " + "ORDER BY bill_id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) 
            {

                Bill bill = mapResultSetToBill(resultSet);

                bills.add(bill);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return bills;
    }

   
    // 3. GET BILL BY ID
    // =========================================
    @Override
    public Bill getBillById(int billId) {

        String sql ="SELECT * FROM bills " + "WHERE bill_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, billId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {

                if (resultSet.next()) 
                {

                    return mapResultSetToBill(resultSet);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    // 4. GET BILL BY APPOINTMENT ID
    // =========================================
    @Override
    public Bill getBillByAppointmentId(int appointmentId) {

        String sql = "SELECT * FROM bills " + "WHERE appointment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, appointmentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) 
                {

                    return mapResultSetToBill(resultSet);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    // =========================================
    // 5. UPDATE PAYMENT STATUS
    // =========================================
    @Override
    public boolean updatePaymentStatus(int billId, String paymentStatus) 
    {

        String sql ="UPDATE bills " + "SET payment_status = ? " + "WHERE bill_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString( 1, paymentStatus);

            preparedStatement.setInt( 2, billId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    // RESULT SET → BILL
    // =========================================
    private Bill mapResultSetToBill(ResultSet resultSet) throws SQLException {

        Bill bill = new Bill();

        bill.setBillId(resultSet.getInt("bill_id"));

        bill.setAppointmentId(resultSet.getInt("appointment_id"));

        bill.setConsultationFee(resultSet.getBigDecimal("consultation_fee"));

        bill.setMedicineCharge(resultSet.getBigDecimal("medicine_charge"));

        bill.setLabCharge(resultSet.getBigDecimal("lab_charge"));

        bill.setTotalAmount(resultSet.getBigDecimal("total_amount"));

        bill.setPaymentStatus(resultSet.getString("payment_status"));

        return bill;
    }
}
