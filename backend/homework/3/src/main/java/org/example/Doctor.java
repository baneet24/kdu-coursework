package org.example;

public class Doctor extends Staff {
    private long doctorId;
    private String specialization;

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