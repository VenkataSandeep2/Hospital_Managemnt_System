package validation;
import exception.InvalidPatientException;
import model.Patient;

import exception.*;

public class PatientValidator {

    public static void validate(Patient patient) 
    {

        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }

        if (patient.getPatientName() == null|| patient.getPatientName().trim().isEmpty()) 
        {
        	throw new IllegalArgumentException("Patient name cannot be empty.");
        }

        if (patient.getAge() <= 0) {

        	throw new InvalidPatientException("Patient age must be greater than 0.");
        }

        if (patient.getGender() == null|| patient.getGender().trim().isEmpty()) 
        {

            throw new IllegalArgumentException("Gender cannot be empty.");
        }

        if (patient.getPhone() == null || !patient.getPhone().matches("\\d{10}")) 
        {

            throw new IllegalArgumentException("Phone number must contain exactly 10 digits.");
        }

        if (patient.getBloodGroup() == null|| patient.getBloodGroup().trim().isEmpty()) 
        {

            throw new IllegalArgumentException( "Blood group cannot be empty.");
        }
    }
}