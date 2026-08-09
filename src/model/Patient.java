package model;

public class Patient {

    private int patientId;
    private String patientName;
    private int age;
    private String gender;
    private String phone;
    private String address;
    private String bloodGroup;

    // Default Constructor
    public Patient() {
    }

    // Parameterized Constructor
    public Patient(int patientId, String patientName, int age, String gender,
                   String phone, String address, String bloodGroup) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Override
    public String toString() {
        return "Patient [patientId=" + patientId +
                ", patientName=" + patientName +
                ", age=" + age +
                ", gender=" + gender +
                ", phone=" + phone +
                ", address=" + address +
                ", bloodGroup=" + bloodGroup + "]";
    }
}