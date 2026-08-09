package serviceimpl;

import java.util.List;

import dao.DepartmentDAO;
import daoimpl.DepartmentDAOImpl;
import model.Department;
import service.DepartmentService;
import validation.DepartmentValidator;

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDAO departmentDAO;

    public DepartmentServiceImpl() {
        departmentDAO = new DepartmentDAOImpl();
    }

    @Override
    public boolean addDepartment(Department department) 
    {

        DepartmentValidator.validate(department);

        return departmentDAO.addDepartment(department);
    }
    
    
    @Override
    public List<Department> getAllDepartments() {

        return departmentDAO.getAllDepartments();
    }

    @Override
    public Department getDepartmentById(int departmentId) {

        return departmentDAO.getDepartmentById(departmentId);
    }

    @Override
    public boolean updateDepartment(Department department) {

        DepartmentValidator.validate(department);

        return departmentDAO.updateDepartment(department);
    }
    
    
    @Override
    public boolean deleteDepartment(int departmentId) {

        return departmentDAO.deleteDepartment(departmentId);
    }
}