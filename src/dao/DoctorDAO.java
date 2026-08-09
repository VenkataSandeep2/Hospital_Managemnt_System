package dao;

import java.util.List;

import model.Doctor;

public interface DoctorDAO {

    boolean addDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();

    Doctor getDoctorById(int doctorId);

    boolean updateDoctor(Doctor doctor);

    boolean deleteDoctor(int doctorId);
}