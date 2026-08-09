package dao;

import java.util.List;
import model.Patient;

public interface PatientDAO 
{

    boolean addPatient(Patient patient);

    List<Patient> getAllPatients();

    Patient getPatientById(int patientId);

    boolean updatePatient(Patient patient);

    boolean deletePatient(int patientId);
}