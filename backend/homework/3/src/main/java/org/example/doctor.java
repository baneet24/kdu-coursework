package org.example;

public class doctor extends staff {
    private long doctorId;
    private String specialization;

    public doctor(long id, String firstName, String lastName, String gender, String email,
                  long staffId, int yearsOfExperience, String description, double salary, boolean insured, healthInsurancePlan patientInsurancePlan, boolean smokes, int age,
                  long doctorId, String specialization) {

        super(id, firstName, lastName, gender, email, staffId, yearsOfExperience, description, salary, insured, patientInsurancePlan, smokes, age);
        this.doctorId = doctorId;
        this.specialization = specialization;
    }

    public void setDoctorId(long doctorId){
        this.doctorId = doctorId;
    }

    public long getDoctorId(){
        return this.doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }



}