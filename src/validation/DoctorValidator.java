package validation;

import java.math.BigDecimal;

import exception.InvalidDoctorException;
import model.Doctor;

public class DoctorValidator {

    public static void validate(Doctor doctor) {

        if (doctor == null) {
            throw new InvalidDoctorException(
                    "Doctor cannot be null.");
        }

        if (doctor.getDoctorName() == null
                || doctor.getDoctorName().trim().isEmpty()) {

            throw new InvalidDoctorException(
                    "Doctor name cannot be empty.");
        }

        if (doctor.getSpecialization() == null
                || doctor.getSpecialization().trim().isEmpty()) {

            throw new InvalidDoctorException(
                    "Specialization cannot be empty.");
        }

        if (doctor.getExperience() < 0) {

            throw new InvalidDoctorException(
                    "Experience cannot be negative.");
        }

        if (doctor.getConsultationFee() == null
                || doctor.getConsultationFee()
                        .compareTo(BigDecimal.ZERO) <= 0) {

            throw new InvalidDoctorException(
                    "Consultation fee must be greater than zero.");
        }

        if (doctor.getDepartmentId() <= 0) {

            throw new InvalidDoctorException(
                    "Department ID must be greater than zero.");
        }
    }
}