package org.example;

public class billing {

    public static double[] computePaymentAmount(patient patient, double amount) {
        double[] payments = new double[2];

        healthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        if(patientInsurancePlan == null){
            payments[0] = 0;
            payments[1] = amount-20;
            return payments;
        }

        double planCoverage = patientInsurancePlan.getCoverage();
        double insuranceAmount = amount*planCoverage;
        double patientAmount = amount - insuranceAmount;

        if(patientInsurancePlan instanceof platinumPlan){
            patientAmount -= 50;
            insuranceAmount+= 50;
        }
        else if(patientInsurancePlan instanceof goldPlan){
            patientAmount -= 40;
            insuranceAmount+= 50;
        }
        else if(patientInsurancePlan instanceof silverPlan){
            patientAmount -= 30;
            insuranceAmount+= 50;
        }
        else if(patientInsurancePlan instanceof bronzePlan){
            patientAmount -= 25;
            insuranceAmount+= 50;
        }


        payments[0] = insuranceAmount;
        if(patientAmount < 0)
            payments[1] = 0;
        else
            payments[1] = patientAmount;

        return payments;
    }

}