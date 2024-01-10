package org.example;

public class Main {
    private static loggerService logger = new loggerService();
    public static void main(String[] args) {

        user staff = new user();
        insuranceBrand insuranceBrand = new BlueCrossBlueShield();

        healthInsurancePlan insurancePlan = new platinumPlan();
        patient patientOne = new patient();
        patientOne.setInsurancePlan(insurancePlan);
        double[] payments = billing.computePaymentAmount(patientOne, 1000.0);
        logger.printLogInfo(String.valueOf(payments[0]));
        logger.printLogInfo(String.valueOf(payments[1]));

        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);
        logger.printLogInfo("Result: " + insurancePlan.computeMonthlyPremium(5000, 56, true));


    }
}