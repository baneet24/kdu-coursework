package org.example;

public class user {
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private boolean insured;
    private healthInsurancePlan patientInsurancePlan;
    private boolean smokes;
    private int age;

    public user(){
    }

    public user(long id, String firstName, String lastName, String gender, String email, boolean insured, healthInsurancePlan patientInsurancePlan, boolean smokes, int age){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.insured = insured;
        this.patientInsurancePlan = patientInsurancePlan;
        this.smokes = smokes;
        this.age = age;
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

    public void setInsurancePlan(healthInsurancePlan patientInsurancePlan){
        this.patientInsurancePlan = patientInsurancePlan;
    }

    public healthInsurancePlan getInsurancePlan(){
        return this.patientInsurancePlan;
    }
}
