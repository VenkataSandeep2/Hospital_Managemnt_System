package service;

import java.util.List;

import model.Appointment;

public interface AppointmentService 
{

    boolean bookAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(int appointmentId);

    List<Appointment> getAppointmentsByPatient(int patientId);

    List<Appointment> getAppointmentsByDoctor(int doctorId);

    boolean cancelAppointment(int appointmentId);
}