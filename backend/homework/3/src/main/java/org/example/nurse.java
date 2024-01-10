package org.example;

public class nurse extends staff {
    private long nurseId;

    public nurse(long id, String firstName, String lastName, String gender, String email,
                 long staffId, int yearsOfExperience, String description, double salary,
                 long nurseId, boolean insured, healthInsurancePlan patientInsurancePlan, boolean smokes, int age) {
        super(id, firstName, lastName, gender, email, staffId, yearsOfExperience, description, salary, insured, patientInsurancePlan, smokes, age);
        this.nurseId = nurseId;
    }

    public long getNurseId() {
        return nurseId;
    }

    public void setNurseId(long nurseId) {
        this.nurseId = nurseId;
    }
}