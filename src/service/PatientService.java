package service;

import java.util.List;

import model.Patient;

public interface PatientService 
	{
	boolean addPatient(Patient patient);
	
	List<Patient> getAllPatients();
	
	 Patient getPatientById(int patientId);
	
	 boolean updatePatient(Patient patient);

	 boolean deletePatient(int patientId);
	}


