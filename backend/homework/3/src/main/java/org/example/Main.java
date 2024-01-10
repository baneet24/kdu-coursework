package org.example;

public class Main {
    private static LoggerService logger = new LoggerService();
    public static void main(String[] args) {

        User staff = new User();
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();

        HealthInsurancePlan insurancePlan = new PlatinumPlan();
        Patient patientOne = new Patient();
        patientOne.setInsurancePlan(insurancePlan);
        double[] payments = Billing.computePaymentAmount(patientOne, 1000.0);
        logger.printLogInfo(String.valueOf(payments[0]));
        logger.printLogInfo(String.valueOf(payments[1]));

        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);
        logger.printLogInfo("Result: " + insurancePlan.computeMonthlyPremium(5000, 56, true));


    }
}