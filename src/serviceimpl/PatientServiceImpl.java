package serviceimpl;

import validation.PatientValidator;
import java.util.List;

import dao.PatientDAO;
import daoimpl.PatientDAOImpl;
import model.Patient;
import service.PatientService;

public class PatientServiceImpl implements PatientService 
{

    private PatientDAO patientDAO;

    public PatientServiceImpl() {
        patientDAO = new PatientDAOImpl();
    }


    @Override
    public boolean addPatient(Patient patient) 
    {

        PatientValidator.validate(patient);

        return patientDAO.addPatient(patient);
    }

	@Override
	public List<Patient> getAllPatients() 
	{
	    return patientDAO.getAllPatients();
	}

	@Override
	public Patient getPatientById(int patientId) 
	{
	    return patientDAO.getPatientById(patientId);
	}

	@Override
	public boolean updatePatient(Patient patient) 
	{

	    PatientValidator.validate(patient);

	    return patientDAO.updatePatient(patient);
	}

	@Override
	public boolean deletePatient(int patientId) 
	{
	    return patientDAO.deletePatient(patientId);
	}


}