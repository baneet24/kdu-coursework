package org.example;

public class patient extends user {
    private long patientId;

    public patient(){

    }

    public patient(long id, String firstName, String lastName, String gender, String email,
                   long patientId, boolean insured,  healthInsurancePlan patientInsurancePlan, boolean smokes, int age) {
        super(id, firstName, lastName, gender, email, insured, patientInsurancePlan, smokes, age);
        this.patientId = patientId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

}
