package dao;

import java.util.List;

import model.Department;

public interface DepartmentDAO {

    boolean addDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(int departmentId);

    boolean updateDepartment(Department department);

    boolean deleteDepartment(int departmentId);
}