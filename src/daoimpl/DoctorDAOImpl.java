package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


import dao.DoctorDAO;
import model.Doctor;
import util.DBConnection;

public class DoctorDAOImpl implements DoctorDAO
{

	@Override
	public boolean addDoctor(Doctor doctor) {

	    String sql = "INSERT INTO doctors "
	               + "(doctor_name, specialization, experience, "
	               + "consultation_fee, department_id) "
	               + "VALUES (?, ?, ?, ?, ?)";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement =
	                 connection.prepareStatement(sql)) {

	        preparedStatement.setString(
	                1, doctor.getDoctorName());

	        preparedStatement.setString(
	                2, doctor.getSpecialization());

	        preparedStatement.setInt(
	                3, doctor.getExperience());

	        preparedStatement.setBigDecimal(
	                4, doctor.getConsultationFee());

	        preparedStatement.setInt(
	                5, doctor.getDepartmentId());

	        int rowsAffected =
	                preparedStatement.executeUpdate();

	        return rowsAffected > 0;

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return false;
	}

	
	@Override
	public List<Doctor> getAllDoctors() {

	    List<Doctor> doctors = new ArrayList<>();

	    String sql = "SELECT * FROM doctors";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement =
	                 connection.prepareStatement(sql);
	         ResultSet resultSet =
	                 preparedStatement.executeQuery()) {

	        while (resultSet.next()) {

	            Doctor doctor = new Doctor();

	            doctor.setDoctorId(
	                    resultSet.getInt("doctor_id"));

	            doctor.setDoctorName(
	                    resultSet.getString("doctor_name"));

	            doctor.setSpecialization(
	                    resultSet.getString("specialization"));

	            doctor.setExperience(
	                    resultSet.getInt("experience"));

	            doctor.setConsultationFee(
	                    resultSet.getBigDecimal("consultation_fee"));

	            doctor.setDepartmentId(
	                    resultSet.getInt("department_id"));

	            doctors.add(doctor);
	        }

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return doctors;
	}

	@Override
	public Doctor getDoctorById(int doctorId) {

	    String sql = "SELECT * FROM doctors WHERE doctor_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement =
	                 connection.prepareStatement(sql)) {

	        preparedStatement.setInt(1, doctorId);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {

	            if (resultSet.next()) {

	                Doctor doctor = new Doctor();

	                doctor.setDoctorId(
	                        resultSet.getInt("doctor_id"));

	                doctor.setDoctorName(
	                        resultSet.getString("doctor_name"));

	                doctor.setSpecialization(
	                        resultSet.getString("specialization"));

	                doctor.setExperience(
	                        resultSet.getInt("experience"));

	                doctor.setConsultationFee(
	                        resultSet.getBigDecimal("consultation_fee"));

	                doctor.setDepartmentId(
	                        resultSet.getInt("department_id"));

	                return doctor;
	            }
	        }

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return null;
	}

	@Override
	public boolean updateDoctor(Doctor doctor) {

	    String sql = "UPDATE doctors "
	               + "SET doctor_name = ?, "
	               + "specialization = ?, "
	               + "experience = ?, "
	               + "consultation_fee = ?, "
	               + "department_id = ? "
	               + "WHERE doctor_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement =
	                 connection.prepareStatement(sql)) {

	        preparedStatement.setString(1, doctor.getDoctorName());
	        preparedStatement.setString(2, doctor.getSpecialization());
	        preparedStatement.setInt(3, doctor.getExperience());
	        preparedStatement.setBigDecimal(4, doctor.getConsultationFee());
	        preparedStatement.setInt(5, doctor.getDepartmentId());
	        preparedStatement.setInt(6, doctor.getDoctorId());

	        int rowsAffected = preparedStatement.executeUpdate();

	        return rowsAffected > 0;

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return false;
	}

	@Override
	public boolean deleteDoctor(int doctorId) {

	    String sql = "DELETE FROM doctors WHERE doctor_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement =
	                 connection.prepareStatement(sql)) {

	        preparedStatement.setInt(1, doctorId);

	        int rowsAffected = preparedStatement.executeUpdate();

	        return rowsAffected > 0;

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return false;
	}

	
	

}
