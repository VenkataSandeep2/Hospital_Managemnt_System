
package model;

import java.math.BigDecimal;

public class Doctor {

    private int doctorId;
    private String doctorName;
    private String specialization;
    private int experience;
    private BigDecimal consultationFee;
    private int departmentId;


    public Doctor() {
    }

    public Doctor(int doctorId, String doctorName,
                  String specialization, int experience,
                  BigDecimal consultationFee, int departmentId) {

        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.experience = experience;
        this.consultationFee = consultationFee;
        this.departmentId = departmentId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public BigDecimal getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(BigDecimal consultationFee) {
        this.consultationFee = consultationFee;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Doctor [doctorId=" + doctorId
                + ", doctorName=" + doctorName
                + ", specialization=" + specialization
                + ", experience=" + experience
                + ", consultationFee=" + consultationFee
                + ", departmentId=" + departmentId + "]";
    }
}
