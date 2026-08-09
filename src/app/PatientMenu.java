
package app;

import java.util.List;

import exception.InvalidPatientException;
import model.Patient;
import service.PatientService;
import serviceimpl.PatientServiceImpl;
import util.InputUtil;

public class PatientMenu {

    private PatientService patientService;

    public PatientMenu() {
        patientService = new PatientServiceImpl();
    }

    // ==============================
    // PATIENT MANAGEMENT MENU
    // ==============================
    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("          PATIENT MANAGEMENT");
            System.out.println("========================================");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Search Patient");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Back");
            System.out.println("========================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addPatient();
                    break;

                case 2:
                    viewAllPatients();
                    break;

                case 3:
                    searchPatient();
                    break;

                case 4:
                    updatePatient();
                    break;

                case 5:
                    deletePatient();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ==============================
    // 1. ADD PATIENT
    // ==============================
    private void addPatient() {

        try {

            Patient patient = new Patient();

            patient.setPatientName(
                    InputUtil.getString("Enter patient name: "));

            patient.setAge(
                    InputUtil.getInt("Enter age: "));

            patient.setGender(
                    InputUtil.getString("Enter gender: "));

            patient.setPhone(
                    InputUtil.getString("Enter phone: "));

            patient.setAddress(
                    InputUtil.getString("Enter address: "));

            patient.setBloodGroup(
                    InputUtil.getString("Enter blood group: "));

            boolean result = patientService.addPatient(patient);

            if (result) {
                System.out.println("Patient added successfully.");
            } else {
                System.out.println("Failed to add patient.");
            }

        } catch (InvalidPatientException e) {

            System.out.println("Validation Error: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println("Please enter a valid number.");
        }
    }

    // ==============================
    // 2. VIEW ALL PATIENTS
    // ==============================
    private void viewAllPatients() {

        List<Patient> patients = patientService.getAllPatients();

        if (patients.isEmpty()) {

            System.out.println("No patients found.");
            return;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("             ALL PATIENTS");
        System.out.println("========================================");

        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    // ==============================
    // 3. SEARCH PATIENT
    // ==============================
    private void searchPatient() {

        try {

            int patientId =
                    InputUtil.getInt("Enter patient ID: ");

            Patient patient =
                    patientService.getPatientById(patientId);

            if (patient != null) {

                System.out.println();
                System.out.println("Patient Found:");
                System.out.println(patient);

            } else {

                System.out.println("Patient not found.");
            }

        } catch (NumberFormatException e) {

            System.out.println("Please enter a valid patient ID.");
        }
    }

    // ==============================
    // 4. UPDATE PATIENT
    // ==============================
    private void updatePatient() {

        try {

            int patientId =
                    InputUtil.getInt("Enter patient ID: ");

            Patient existingPatient =
                    patientService.getPatientById(patientId);

            if (existingPatient == null) {

                System.out.println("Patient not found.");
                return;
            }

            System.out.println("Current Patient Details:");
            System.out.println(existingPatient);

            System.out.println();
            System.out.println("Enter new patient details:");

            Patient patient = new Patient();

            patient.setPatientId(patientId);

            patient.setPatientName(
                    InputUtil.getString("Enter patient name: "));

            patient.setAge(
                    InputUtil.getInt("Enter age: "));

            patient.setGender(
                    InputUtil.getString("Enter gender: "));

            patient.setPhone(
                    InputUtil.getString("Enter phone: "));

            patient.setAddress(
                    InputUtil.getString("Enter address: "));

            patient.setBloodGroup(
                    InputUtil.getString("Enter blood group: "));

            boolean result =
                    patientService.updatePatient(patient);

            if (result) {

                System.out.println(
                        "Patient updated successfully.");

            } else {

                System.out.println(
                        "Patient failed to update.");
            }

        } catch (InvalidPatientException e) {

            System.out.println(
                    "Validation Error: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter valid numbers.");
        }
    }

    // ==============================
    // 5. DELETE PATIENT
    // ==============================
    private void deletePatient() {

        try {

            int patientId =
                    InputUtil.getInt("Enter patient ID: ");

            Patient patient =
                    patientService.getPatientById(patientId);

            if (patient == null) {

                System.out.println("Patient not found.");
                return;
            }

            System.out.println("Patient to be deleted:");
            System.out.println(patient);

            String confirmation =
                    InputUtil.getString(
                            "Are you sure you want to delete? (yes/no): ");

            if (!confirmation.equalsIgnoreCase("yes")) {

                System.out.println("Delete operation cancelled.");
                return;
            }

            boolean result =
                    patientService.deletePatient(patientId);

            if (result) {

                System.out.println(
                        "Patient deleted successfully.");

            } else {

                System.out.println(
                        "Patient failed to delete.");
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid patient ID.");
        }
    }
}

