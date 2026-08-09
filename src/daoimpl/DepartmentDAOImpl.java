package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dao.DepartmentDAO;
import model.Department;
import util.DBConnection;

public class DepartmentDAOImpl implements DepartmentDAO {

	@Override
	public boolean addDepartment(Department department) {
		String sql = "insert into departments (department_name)"+ "values(?)";
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql ))
		{
			preparedStatement.setString(1,department.getDepartmentName());
			int rowsAffected = preparedStatement.executeUpdate();
			
			return rowsAffected >0;
			
		} 		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	
	

	@Override
	public List<Department> getAllDepartments() {
		List<Department> departments = new ArrayList<>();
		String sql = "Select * from departments";
		
		 try (Connection connection = DBConnection.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(sql);
		         ResultSet resultSet = preparedStatement.executeQuery()) 
		 {
			 while (resultSet.next())
			 {
				 Department department = new Department();
				 
				 department.setDepartmentId(resultSet.getInt("department_id"));
				 
				 department.setDepartmentName(resultSet.getString("department_name"));
				 
				 departments.add(department);
			 }
			 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return departments;
	}
	
	
	

	@Override
	public Department getDepartmentById(int departmentId) {

	    String sql = "SELECT * FROM departments "
	               + "WHERE department_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement =
	                 connection.prepareStatement(sql)) {

	        preparedStatement.setInt(1, departmentId);

	        try (ResultSet resultSet =
	                     preparedStatement.executeQuery()) {

	            if (resultSet.next()) {

	                Department department = new Department();

	                department.setDepartmentId(
	                        resultSet.getInt("department_id"));

	                department.setDepartmentName(
	                        resultSet.getString("department_name"));

	                return department;
	            }
	        }

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return null;
	}
	
	

	@Override
	public boolean updateDepartment(Department department) {

	    String sql = "UPDATE departments "
	               + "SET department_name = ? "
	               + "WHERE department_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement =
	                 connection.prepareStatement(sql)) {

	        preparedStatement.setString(
	                1, department.getDepartmentName());

	        preparedStatement.setInt(
	                2, department.getDepartmentId());

	        int rowsAffected =
	                preparedStatement.executeUpdate();

	        return rowsAffected > 0;

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return false;
	}
	
	
	

	@Override
	public boolean deleteDepartment(int departmentId) {

	    String sql = "DELETE FROM departments "
	               + "WHERE department_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement =
	                 connection.prepareStatement(sql)) {

	        preparedStatement.setInt(1, departmentId);

	        int rowsAffected =
	                preparedStatement.executeUpdate();

	        return rowsAffected > 0;

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return false;
	}

}