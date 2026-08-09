package dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import model.Appointment;

public interface AppointmentDAO {

    boolean bookAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(int appointmentId);

    List<Appointment> getAppointmentsByPatient(int patientId);

    List<Appointment> getAppointmentsByDoctor(int doctorId);

    boolean cancelAppointment(int appointmentId);

    boolean isDoctorAvailable(
            int doctorId,LocalDate appointmentDate,LocalTime appointmentTime);
}