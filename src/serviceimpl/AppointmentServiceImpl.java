package serviceimpl;

import java.util.List;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import dao.PatientDAO;
import daoimpl.AppointmentDAOImpl;
import daoimpl.DoctorDAOImpl;
import daoimpl.PatientDAOImpl;
import exception.InvalidAppointmentException;
import model.Appointment;
import service.AppointmentService;
import validation.AppointmentValidator;

public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentDAO appointmentDAO;
    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;

    public AppointmentServiceImpl() {

        appointmentDAO = new AppointmentDAOImpl();
        patientDAO = new PatientDAOImpl();
        doctorDAO = new DoctorDAOImpl();
    }

    @Override
    public boolean bookAppointment(Appointment appointment) {

        AppointmentValidator.validate(appointment);

        if (patientDAO.getPatientById(
                appointment.getPatientId()) == null) {

            throw new InvalidAppointmentException(
                    "Patient not found with ID: "
                    + appointment.getPatientId());
        }

        if (doctorDAO.getDoctorById(
                appointment.getDoctorId()) == null) {

            throw new InvalidAppointmentException(
                    "Doctor not found with ID: "
                    + appointment.getDoctorId());
        }

        if (!appointmentDAO.isDoctorAvailable(
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime())) {

            throw new InvalidAppointmentException(
                    "Doctor is already booked for this date and time.");
        }

        return appointmentDAO.bookAppointment(appointment);
    }
    @Override
    public List<Appointment> getAllAppointments() {

        return appointmentDAO.getAllAppointments();
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {

        return appointmentDAO.getAppointmentById(appointmentId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(
            int patientId) {

        return appointmentDAO.getAppointmentsByPatient(
                patientId);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(
            int doctorId) {

        return appointmentDAO.getAppointmentsByDoctor(
                doctorId);
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {

        return appointmentDAO.cancelAppointment(
                appointmentId);
    }
}
