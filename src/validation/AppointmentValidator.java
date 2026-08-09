package validation;

import java.time.LocalDate;
import java.time.LocalTime;

import exception.InvalidAppointmentException;
import model.Appointment;

public class AppointmentValidator {

    public static void validate(Appointment appointment) {

        if (appointment == null) {

            throw new InvalidAppointmentException(
                    "Appointment cannot be null.");
        }

        if (appointment.getPatientId() <= 0) {

            throw new InvalidAppointmentException(
                    "Patient ID must be greater than zero.");
        }

        if (appointment.getDoctorId() <= 0) {

            throw new InvalidAppointmentException(
                    "Doctor ID must be greater than zero.");
        }

        if (appointment.getAppointmentDate() == null) {

            throw new InvalidAppointmentException(
                    "Appointment date cannot be null.");
        }

        if (appointment.getAppointmentTime() == null) {

            throw new InvalidAppointmentException(
                    "Appointment time cannot be null.");
        }

        if (appointment.getAppointmentDate()
                .isBefore(LocalDate.now())) {

            throw new InvalidAppointmentException(
                    "Appointment date cannot be in the past.");
        }

        if (appointment.getStatus() == null
                || appointment.getStatus().trim().isEmpty()) {

            throw new InvalidAppointmentException(
                    "Appointment status cannot be empty.");
        }
    }
}