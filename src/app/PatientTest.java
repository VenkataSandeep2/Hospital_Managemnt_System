package app;

import exception.InvalidPatientException;
import model.Patient;
import service.PatientService;
import serviceimpl.PatientServiceImpl;

public class PatientTest {

    public static void main(String[] args) {

        PatientService patientService = new PatientServiceImpl();

        Patient patient = new Patient();

        patient.setPatientName("Naveen");
        patient.setAge(20);
        patient.setGender("Male");
        patient.setPhone("9989616021");
        patient.setAddress("HYD");
        patient.setBloodGroup("B+");

        try {

            boolean result = patientService.addPatient(patient);

            if (result) {
                System.out.println("Patient added successfully.");
            } else {
                System.out.println("Patient failed to add.");
            }

        } catch (InvalidPatientException e) {

            System.out.println("Validation Error: " + e.getMessage());
        }
    }
}