package app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import exception.InvalidAppointmentException;
import model.Appointment;
import service.AppointmentService;
import serviceimpl.AppointmentServiceImpl;
import util.InputUtil;

public class AppointmentMenu {

    private AppointmentService appointmentService;

    public AppointmentMenu() {
        appointmentService = new AppointmentServiceImpl();
    }

    // =========================================
    // APPOINTMENT MANAGEMENT MENU
    // =========================================
    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("       APPOINTMENT MANAGEMENT");
            System.out.println("========================================");
            System.out.println("1. Book Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. Search Appointment");
            System.out.println("4. Patient Appointment History");
            System.out.println("5. Doctor Schedule");
            System.out.println("6. Cancel Appointment");
            System.out.println("7. Back");
            System.out.println("========================================");

            int choice =
                    InputUtil.getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    bookAppointment();
                    break;

                case 2:
                    viewAllAppointments();
                    break;

                case 3:
                    searchAppointment();
                    break;

                case 4:
                    patientAppointmentHistory();
                    break;

                case 5:
                    doctorSchedule();
                    break;

                case 6:
                    cancelAppointment();
                    break;

                case 7:
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again.");
            }
        }
    }

    // =========================================
    // 1. BOOK APPOINTMENT
    // =========================================
    private void bookAppointment() {

        try {

            Appointment appointment = new Appointment();

            appointment.setPatientId(
                    InputUtil.getInt("Enter patient ID: "));

            appointment.setDoctorId(InputUtil.getInt("Enter doctor ID: "));

            String date =
                    InputUtil.getString("Enter appointment date (YYYY-MM-DD): ");

            appointment.setAppointmentDate(LocalDate.parse(date));

            String time =InputUtil.getString("Enter appointment time (HH:MM): ");

            appointment.setAppointmentTime(LocalTime.parse(time));

            appointment.setStatus("BOOKED");

            boolean result = appointmentService.bookAppointment(appointment);

            if (result) {

                System.out.println("Appointment booked successfully.");

            } else {

                System.out.println("Appointment booking failed.");
            }

        } catch (InvalidAppointmentException e) 
        {

            System.out.println("Validation Error: "+ e.getMessage());

        } catch (Exception e) {

            System.out.println("Unable to book appointment: "+ e.getMessage());
        }
    }

    // =========================================
    // 2. VIEW ALL APPOINTMENTS
    // =========================================
    private void viewAllAppointments() {

        List<Appointment> appointments =appointmentService.getAllAppointments();

        if (appointments.isEmpty()) 
        {

            System.out.println("No appointments found.");

            return;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("          ALL APPOINTMENTS");
        System.out.println("========================================");

        for (Appointment appointment : appointments) {

            System.out.println(appointment);
        }
    }

    // =========================================
    // 3. SEARCH APPOINTMENT
    // =========================================
    private void searchAppointment() 
    {

        try {

            int appointmentId =InputUtil.getInt("Enter appointment ID: ");

            Appointment appointment =
                    appointmentService.getAppointmentById(appointmentId);

            if (appointment != null) 
            {

                System.out.println();
                System.out.println("Appointment Found:");

                System.out.println(appointment);

            } else {

                System.out.println("Appointment not found.");
            }

        } catch (Exception e) 
        {

            System.out.println("Unable to search appointment: " + e.getMessage());
        }
    }

    // =========================================
    // 4. PATIENT APPOINTMENT HISTORY
    // =========================================
    private void patientAppointmentHistory() {

        try {

            int patientId =InputUtil.getInt("Enter patient ID: ");

            List<Appointment> appointments =
                    appointmentService.getAppointmentsByPatient(patientId);

            if (appointments.isEmpty()) {

                System.out.println("No appointments found for this patient.");

                return;
            }

            System.out.println();
            System.out.println("========================================");

            System.out.println("PATIENT APPOINTMENT HISTORY");

            System.out.println("========================================");

            for (Appointment appointment :appointments) 
            {

                System.out.println(appointment);
            }

        } catch (Exception e) {

            System.out.println("Unable to retrieve patient history: "+ e.getMessage());
        }
    }

    // =========================================
    // 5. DOCTOR SCHEDULE
    // =========================================
    private void doctorSchedule() 
    {

        try {

            int doctorId =InputUtil.getInt("Enter doctor ID: ");

            List<Appointment> appointments =
                    appointmentService.getAppointmentsByDoctor(doctorId);

            if (appointments.isEmpty()) {

                System.out.println("No appointments found for this doctor.");

                return;
            }

            System.out.println();
            System.out.println("========================================");

            System.out.println("DOCTOR SCHEDULE");

            System.out.println("========================================");

            for (Appointment appointment :appointments) 
            {

                System.out.println(appointment);
            }

        } catch (Exception e) {

            System.out.println("Unable to retrieve doctor schedule: "+ e.getMessage());
        }
    }

    // =========================================
    // 6. CANCEL APPOINTMENT
    // =========================================
    private void cancelAppointment() {

        try {

            int appointmentId =
                    InputUtil.getInt("Enter appointment ID: ");

            Appointment appointment =
                    appointmentService.getAppointmentById(appointmentId);

            if (appointment == null)
            {

                System.out.println("Appointment not found.");

                return;
            }

            System.out.println();
            System.out.println("Appointment to be cancelled:");

            System.out.println(appointment);

            if ("CANCELLED".equalsIgnoreCase(appointment.getStatus())) {

                System.out.println("Appointment is already cancelled.");

                return;
            }

            String confirmation =
                    InputUtil.getString("Are you sure? (yes/no): ");

            if (!confirmation.equalsIgnoreCase("yes")) {

                System.out.println("Cancellation aborted.");

                return;
            }

            boolean result =
                    appointmentService
                            .cancelAppointment(
                                    appointmentId);

            if (result) {

                System.out.println("Appointment cancelled successfully.");

            } else {

                System.out.println("Appointment cancellation failed.");
            }

        } catch (Exception e) {

            System.out.println("Unable to cancel appointment: " + e.getMessage());
        }
    }
}
