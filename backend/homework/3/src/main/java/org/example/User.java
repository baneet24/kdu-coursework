package org.example;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private boolean insured;
    private HealthInsurancePlan patientInsurancePlan;
    private boolean smokes;
    private int age;

    public User(){
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSmokes(boolean smokes){
        this.smokes = smokes;
    }

    public boolean isSmokes(){
        return this.smokes;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    public boolean isInsured() {
        return insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    public void setInsurancePlan(HealthInsurancePlan patientInsurancePlan){
        this.patientInsurancePlan = patientInsurancePlan;
    }

    public HealthInsurancePlan getInsurancePlan(){
        return this.patientInsurancePlan;
    }
}
