package validation;

import exception.InvalidDepartmentException;
import model.Department;

public class DepartmentValidator {

    public static void validate(Department department) {

        if (department == null) {

            throw new InvalidDepartmentException("Department cannot be null.");
        }

        if (department.getDepartmentName() == null || department.getDepartmentName().trim().isEmpty()) {

            throw new InvalidDepartmentException("Department name cannot be empty.");
        }

        if (department.getDepartmentName().trim().length() < 3) 
        {

            throw new InvalidDepartmentException("Department name must contain at least 3 characters.");
        }

        if (department.getDepartmentName().trim().length() > 100) 
        {

            throw new InvalidDepartmentException("Department name cannot exceed 100 characters.");
        }
    }
}