package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PatientDAO;
import model.Patient;
import util.DBConnection;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public boolean addPatient(Patient patient) {

        String sql = "INSERT INTO patients "
                   + "(patient_name, age, gender, phone, address, blood_group) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, patient.getPatientName());
            preparedStatement.setInt(2, patient.getAge());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setString(4, patient.getPhone());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setString(6, patient.getBloodGroup());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Patient> getAllPatients() {

        List<Patient> patients = new ArrayList<>();

        String sql = "SELECT * FROM patients";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                Patient patient = new Patient();

                patient.setPatientId(resultSet.getInt("patient_id"));
                patient.setPatientName(resultSet.getString("patient_name"));
                patient.setAge(resultSet.getInt("age"));
                patient.setGender(resultSet.getString("gender"));
                patient.setPhone(resultSet.getString("phone"));
                patient.setAddress(resultSet.getString("address"));
                patient.setBloodGroup(resultSet.getString("blood_group"));

                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }
	@Override
	public Patient getPatientById(int patientId) {
		
		Patient patient = null;
		String sql =  "select * from patients where patient_id =?";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
		preparedStatement.setInt(1, patientId);	
		try(ResultSet resultSet = preparedStatement.executeQuery())
		{
			if(resultSet.next())
			{
				patient = new Patient();
				patient.setPatientId(resultSet.getInt("patient_id"));
	            patient.setPatientName(resultSet.getString("patient_name"));
	            patient.setAge(resultSet.getInt("age"));
	            patient.setGender(resultSet.getString("gender"));
	            patient.setPhone(resultSet.getString("phone"));
	            patient.setAddress(resultSet.getString("address"));
	            patient.setBloodGroup(resultSet.getString("blood_group"));
			}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return patient;
	}

	@Override
	public boolean updatePatient(Patient patient) {

	    String sql = "UPDATE patients "
	               + "SET patient_name = ?, age = ?, gender = ?, "
	               + "phone = ?, address = ?, blood_group = ? "
	               + "WHERE patient_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        preparedStatement.setString(1, patient.getPatientName());
	        preparedStatement.setInt(2, patient.getAge());
	        preparedStatement.setString(3, patient.getGender());
	        preparedStatement.setString(4, patient.getPhone());
	        preparedStatement.setString(5, patient.getAddress());
	        preparedStatement.setString(6, patient.getBloodGroup());
	        preparedStatement.setInt(7, patient.getPatientId());

	        int rowsAffected = preparedStatement.executeUpdate();

	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	

	@Override
	public boolean deletePatient(int patientId) {

	    String sql = "DELETE FROM patients WHERE patient_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        preparedStatement.setInt(1, patientId);

	        int rowsAffected = preparedStatement.executeUpdate();

	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
}