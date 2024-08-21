package org.example;

public class Billing {

    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        if(patientInsurancePlan == null){
            payments[0] = 0;
            payments[1] = amount-20;
            return payments;
        }

        double planCoverage = patientInsurancePlan.getCoverage();
        double insuranceAmount = amount*planCoverage;
        double patientAmount = amount - insuranceAmount;

        if(patientInsurancePlan instanceof PlatinumPlan){
            patientAmount -= 50;
            insuranceAmount+= 50;
        }
        else if(patientInsurancePlan instanceof GoldPlan){
            patientAmount -= 40;
            insuranceAmount+= 50;
        }
        else if(patientInsurancePlan instanceof SilverPlan){
            patientAmount -= 30;
            insuranceAmount+= 50;
        }
        else if(patientInsurancePlan instanceof BronzePlan){
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