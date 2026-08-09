
package serviceimpl;

import java.util.List;

import dao.DoctorDAO;
import daoimpl.DoctorDAOImpl;
import model.Doctor;
import service.DoctorService;
import validation.DoctorValidator;

public class DoctorServiceImpl implements DoctorService {

    private DoctorDAO doctorDAO;

    public DoctorServiceImpl() {
        doctorDAO = new DoctorDAOImpl();
    }

    @Override
    public boolean addDoctor(Doctor doctor) {

        DoctorValidator.validate(doctor);

        return doctorDAO.addDoctor(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {

        return doctorDAO.getAllDoctors();
    }

    @Override
    public Doctor getDoctorById(int doctorId) {

        return doctorDAO.getDoctorById(doctorId);
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {

        DoctorValidator.validate(doctor);

        return doctorDAO.updateDoctor(doctor);
    }

    @Override
    public boolean deleteDoctor(int doctorId) {

        return doctorDAO.deleteDoctor(doctorId);
    }
}

