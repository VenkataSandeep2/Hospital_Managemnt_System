package app;

import java.util.List;

import exception.InvalidDepartmentException;
import model.Department;
import service.DepartmentService;
import serviceimpl.DepartmentServiceImpl;
import util.InputUtil;

public class DepartmentMenu 
{

    private DepartmentService departmentService;

    public DepartmentMenu() {
        departmentService = new DepartmentServiceImpl();
    }

        // DEPARTMENT MANAGEMENT MENU
    // =========================================
    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("        DEPARTMENT MANAGEMENT");
            System.out.println("========================================");
            System.out.println("1. Add Department");
            System.out.println("2. View All Departments");
            System.out.println("3. Search Department");
            System.out.println("4. Update Department");
            System.out.println("5. Delete Department");
            System.out.println("6. Back");
            System.out.println("========================================");

            int choice =
                    InputUtil.getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addDepartment();
                    break;

                case 2:
                    viewAllDepartments();
                    break;

                case 3:
                    searchDepartment();
                    break;

                case 4:
                    updateDepartment();
                    break;

                case 5:
                    deleteDepartment();
                    break;

                case 6:
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again.");
            }
        }
    }

    // 1. ADD DEPARTMENT
    // =========================================
    private void addDepartment() {

        try {

            Department department = new Department();

            department.setDepartmentName(InputUtil.getString("Enter department name: "));

            boolean result = departmentService.addDepartment(department);

            if (result) 
            {
            	System.out.println("Department added successfully.");

            } else 
            {
            	System.out.println("Department failed to add.");
            }

        } catch (InvalidDepartmentException e) {

            System.out.println("Validation Error: " + e.getMessage());

        } catch (Exception e) {

            System.out.println( "Unable to add department: " + e.getMessage());
        }
    }

    // =========================================
    // 2. VIEW ALL DEPARTMENTS
    // =========================================
    private void viewAllDepartments() {

        List<Department> departments =departmentService.getAllDepartments();

        if (departments.isEmpty()) {

            System.out.println( "No departments found.");

            return;
        }

        System.out.println();
        System.out.println("          ALL DEPARTMENTS");
        System.out.println("========================================");

        for (Department department : departments) 
        {

            System.out.println(department);
        }
    }

    // 3. SEARCH DEPARTMENT
    // =========================================
    private void searchDepartment() {

        try {

            int departmentId = InputUtil.getInt( "Enter department ID: ");

            Department department = departmentService.getDepartmentById(departmentId);

            if (department != null) 
            {

                System.out.println();
                System.out.println( "Department Found:");

                System.out.println(department);

            } else {

                System.out.println("Department not found.");
            }

        } catch (NumberFormatException e) {

            System.out.println("Please enter a valid department ID.");
        }
    }
    // 4. UPDATE DEPARTMENT
    // =========================================
    private void updateDepartment()
    {

        try {

            int departmentId = InputUtil.getInt("Enter department ID: ");

            Department existingDepartment = departmentService.getDepartmentById(departmentId);

            if (existingDepartment == null) 
            {

                System.out.println("Department not found.");

                return;
            }

            System.out.println();
            System.out.println("Current Department Details:");

            System.out.println(existingDepartment);

            System.out.println();
            System.out.println("Enter new department details:");

            Department department = new Department();

            department.setDepartmentId(departmentId);

            department.setDepartmentName(InputUtil.getString("Enter department name: "));

            boolean result = departmentService.updateDepartment(department);

            if (result) {

                System.out.println("Department updated successfully.");

            } else {

                System.out.println("Department failed to update.");
            }

        } catch (InvalidDepartmentException e) {

            System.out.println("Validation Error: " + e.getMessage());

        } catch (Exception e) {

            System.out.println("Unable to update department: " + e.getMessage());
        }
    }

    // 5. DELETE DEPARTMENT
    // =========================================
    private void deleteDepartment() {

        try {

            int departmentId = InputUtil.getInt( "Enter department ID: ");

            Department department = departmentService.getDepartmentById(departmentId);

            if (department == null) {

                System.out.println("Department not found.");

                return;
            }

            System.out.println();
            System.out.println("Department to be deleted:");

            System.out.println(department);

            String confirmation =
                    InputUtil.getString("Are you sure you want to delete? (yes/no): ");

            if (!confirmation.equalsIgnoreCase("yes")) {

                System.out.println("Delete operation cancelled.");

                return;
            }

            boolean result = departmentService.deleteDepartment(departmentId);

            if (result) {

                System.out.println("Department deleted successfully.");

            } else {

                System.out.println("Department failed to delete.");
            }

        } catch (Exception e) {

            System.out.println("Unable to delete department: "
                    + e.getMessage());
        }
    }
}

