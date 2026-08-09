package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dao.AppointmentDAO;
import model.Appointment;
import util.DBConnection;

public class AppointmentDAOImpl implements AppointmentDAO {

    // =========================================
    // 1. BOOK APPOINTMENT
    // =========================================
    @Override
    public boolean bookAppointment(Appointment appointment) {

        String sql = "INSERT INTO appointments "
                   + "(patient_id, doctor_id, appointment_date, "
                   + "appointment_time, status) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(
                    1, appointment.getPatientId());

            preparedStatement.setInt(
                    2, appointment.getDoctorId());

            preparedStatement.setDate(
                    3,
                    java.sql.Date.valueOf(
                            appointment.getAppointmentDate()));

            preparedStatement.setTime(
                    4,
                    java.sql.Time.valueOf(
                            appointment.getAppointmentTime()));

            preparedStatement.setString(
                    5, appointment.getStatus());

            int rowsAffected =
                    preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    // =========================================
    // 2. GET ALL APPOINTMENTS
    // =========================================
    @Override
    public List<Appointment> getAllAppointments() {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql =
                "SELECT * FROM appointments "
              + "ORDER BY appointment_date, appointment_time";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                Appointment appointment =
                        mapResultSetToAppointment(resultSet);

                appointments.add(appointment);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return appointments;
    }

    // =========================================
    // 3. GET APPOINTMENT BY ID
    // =========================================
    @Override
    public Appointment getAppointmentById(int appointmentId) {

        String sql =
                "SELECT * FROM appointments "
              + "WHERE appointment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, appointmentId);

            try (ResultSet resultSet =
                         preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return mapResultSetToAppointment(
                            resultSet);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    // =========================================
    // 4. GET APPOINTMENTS BY PATIENT
    // =========================================
    @Override
    public List<Appointment> getAppointmentsByPatient(int patientId)
    {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql =
                "SELECT * FROM appointments "
              + "WHERE patient_id = ? "
              + "ORDER BY appointment_date, appointment_time";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, patientId);

            try (ResultSet resultSet =
                         preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    Appointment appointment =
                            mapResultSetToAppointment(
                                    resultSet);

                    appointments.add(appointment);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return appointments;
    }

    // =========================================
    // 5. GET APPOINTMENTS BY DOCTOR
    // =========================================
    @Override
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql =
                "SELECT * FROM appointments "
              + "WHERE doctor_id = ? "
              + "AND status = 'BOOKED' "
              + "AND TIMESTAMP(appointment_date, appointment_time) >= NOW() "
              + "ORDER BY appointment_date, appointment_time";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, doctorId);

            try (ResultSet resultSet =
                         preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    Appointment appointment =
                            mapResultSetToAppointment(resultSet);

                    appointments.add(appointment);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return appointments;
    }
    // =========================================
    // 6. CANCEL APPOINTMENT
    // =========================================
    @Override
    public boolean cancelAppointment(int appointmentId) {

        String sql =
                "UPDATE appointments "
              + "SET status = ? "
              + "WHERE appointment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "CANCELLED");
            preparedStatement.setInt(2, appointmentId);

            int rowsAffected =
                    preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    // =========================================
    // RESULT SET → APPOINTMENT
    // =========================================
    private Appointment mapResultSetToAppointment(
            ResultSet resultSet) throws SQLException {

        Appointment appointment =
                new Appointment();

        appointment.setAppointmentId(
                resultSet.getInt("appointment_id"));

        appointment.setPatientId(
                resultSet.getInt("patient_id"));

        appointment.setDoctorId(
                resultSet.getInt("doctor_id"));

        appointment.setAppointmentDate(
                resultSet.getDate("appointment_date")
                        .toLocalDate());

        appointment.setAppointmentTime(
                resultSet.getTime("appointment_time")
                        .toLocalTime());

        appointment.setStatus(
                resultSet.getString("status"));

        return appointment;
    }

    @Override
    public boolean isDoctorAvailable(
            int doctorId,
            LocalDate appointmentDate,
            LocalTime appointmentTime) {

        String sql = "SELECT COUNT(*) FROM appointments "
                   + "WHERE doctor_id = ? "
                   + "AND appointment_date = ? "
                   + "AND appointment_time = ? "
                   + "AND status = 'BOOKED'";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, doctorId);

            preparedStatement.setDate(
                    2,
                    java.sql.Date.valueOf(appointmentDate));

            preparedStatement.setTime(
                    3,
                    java.sql.Time.valueOf(appointmentTime));

            try (ResultSet resultSet =
                         preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    int count = resultSet.getInt(1);

                    return count == 0;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }
	}
