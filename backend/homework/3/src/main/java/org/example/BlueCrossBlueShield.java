package org.example;

public class BlueCrossBlueShield implements insuranceBrand {
    public double computeMonthlyPremium(healthInsurancePlan insurancePlan, int age, boolean smoking){
        double premiumAmount = 0;
        if(insurancePlan instanceof platinumPlan) {
            if (age > 55) {
                premiumAmount += 200.0;
            }
            if (smoking) {
                premiumAmount += 100.0;
            }
        }

        else if(insurancePlan instanceof goldPlan){
            if (age > 55) {
                premiumAmount += 150.0;
            }
            if (smoking) {
                premiumAmount += 90.0;
            }
        }

        else if(insurancePlan instanceof silverPlan){
            if (age > 55) {
                premiumAmount += 50.0;
            }
            if (smoking) {
                premiumAmount += 80.0;
            }
        }

        else if(insurancePlan instanceof bronzePlan){
            if (age > 55) {
                premiumAmount += 50.0;
            }
            if (smoking) {
                premiumAmount += 70.0;
            }
        }

        return premiumAmount;
    }
}
