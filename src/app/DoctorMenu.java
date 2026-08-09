
package app;

import java.math.BigDecimal;
import java.util.List;

import exception.InvalidDoctorException;
import model.Doctor;
import service.DoctorService;
import serviceimpl.DoctorServiceImpl;
import util.InputUtil;

public class DoctorMenu {

    private DoctorService doctorService;

    public DoctorMenu() {
        doctorService = new DoctorServiceImpl();
    }

    // =========================================
    // DOCTOR MANAGEMENT MENU
    // =========================================
    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("           DOCTOR MANAGEMENT");
            System.out.println("========================================");
            System.out.println("1. Add Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. Search Doctor");
            System.out.println("4. Update Doctor");
            System.out.println("5. Delete Doctor");
            System.out.println("6. Back");
            System.out.println("========================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addDoctor();
                    break;

                case 2:
                    viewAllDoctors();
                    break;

                case 3:
                    searchDoctor();
                    break;

                case 4:
                    updateDoctor();
                    break;

                case 5:
                    deleteDoctor();
                    break;

                case 6:
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again.");
            }
        }
    }

    // =========================================
    // 1. ADD DOCTOR
    // =========================================
    private void addDoctor() {

        try {

            Doctor doctor = new Doctor();

            doctor.setDoctorName(
                    InputUtil.getString("Enter doctor name: "));

            doctor.setSpecialization(
                    InputUtil.getString("Enter specialization: "));

            doctor.setExperience(
                    InputUtil.getInt("Enter experience: "));

            String fee =
                    InputUtil.getString("Enter consultation fee: ");

            doctor.setConsultationFee(
                    new BigDecimal(fee));

            doctor.setDepartmentId(
                    InputUtil.getInt("Enter department ID: "));

            boolean result =
                    doctorService.addDoctor(doctor);

            if (result) {

                System.out.println(
                        "Doctor added successfully.");

            } else {

                System.out.println(
                        "Doctor failed to add.");
            }

        } catch (InvalidDoctorException e) {

            System.out.println(
                    "Validation Error: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter valid numeric values.");
        }
    }

    // =========================================
    // 2. VIEW ALL DOCTORS
    // =========================================
    private void viewAllDoctors() {

        List<Doctor> doctors =
                doctorService.getAllDoctors();

        if (doctors.isEmpty()) {

            System.out.println("No doctors found.");
            return;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("             ALL DOCTORS");
        System.out.println("========================================");

        for (Doctor doctor : doctors) {

            System.out.println(doctor);
        }
    }

    // =========================================
    // 3. SEARCH DOCTOR
    // =========================================
    private void searchDoctor() {

        try {

            int doctorId =
                    InputUtil.getInt("Enter doctor ID: ");

            Doctor doctor =
                    doctorService.getDoctorById(doctorId);

            if (doctor != null) {

                System.out.println();
                System.out.println("Doctor Found:");
                System.out.println(doctor);

            } else {

                System.out.println(
                        "Doctor not found.");
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid doctor ID.");
        }
    }

    // =========================================
    // 4. UPDATE DOCTOR
    // =========================================
    private void updateDoctor() {

        try {

            int doctorId =
                    InputUtil.getInt("Enter doctor ID: ");

            Doctor existingDoctor =
                    doctorService.getDoctorById(doctorId);

            if (existingDoctor == null) {

                System.out.println(
                        "Doctor not found.");

                return;
            }

            System.out.println();
            System.out.println("Current Doctor Details:");
            System.out.println(existingDoctor);

            System.out.println();
            System.out.println("Enter new doctor details:");

            Doctor doctor = new Doctor();

            doctor.setDoctorId(doctorId);

            doctor.setDoctorName(
                    InputUtil.getString(
                            "Enter doctor name: "));

            doctor.setSpecialization(
                    InputUtil.getString(
                            "Enter specialization: "));

            doctor.setExperience(
                    InputUtil.getInt(
                            "Enter experience: "));

            String fee =
                    InputUtil.getString(
                            "Enter consultation fee: ");

            doctor.setConsultationFee(
                    new BigDecimal(fee));

            doctor.setDepartmentId(
                    InputUtil.getInt(
                            "Enter department ID: "));

            boolean result =
                    doctorService.updateDoctor(doctor);

            if (result) {

                System.out.println(
                        "Doctor updated successfully.");

            } else {

                System.out.println(
                        "Doctor failed to update.");
            }

        } catch (InvalidDoctorException e) {

            System.out.println(
                    "Validation Error: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter valid numeric values.");
        }
    }

    // =========================================
    // 5. DELETE DOCTOR
    // =========================================
    private void deleteDoctor() {

        try {

            int doctorId =
                    InputUtil.getInt(
                            "Enter doctor ID: ");

            Doctor doctor =
                    doctorService.getDoctorById(doctorId);

            if (doctor == null) {

                System.out.println(
                        "Doctor not found.");

                return;
            }

            System.out.println();
            System.out.println(
                    "Doctor to be deleted:");

            System.out.println(doctor);

            String confirmation =
                    InputUtil.getString(
                            "Are you sure you want to delete? (yes/no): ");

            if (!confirmation.equalsIgnoreCase("yes")) {

                System.out.println(
                        "Delete operation cancelled.");

                return;
            }

            boolean result =
                    doctorService.deleteDoctor(doctorId);

            if (result) {

                System.out.println(
                        "Doctor deleted successfully.");

            } else {

                System.out.println(
                        "Doctor failed to delete.");
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid doctor ID.");
        }
    }
}
