package org.example;

public class BlueCrossBlueShield implements InsuranceBrand {
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking){
        double premiumAmount = 0;
        if(insurancePlan instanceof PlatinumPlan) {
            if (age > 55) {
                premiumAmount += 200.0;
            }
            if (smoking) {
                premiumAmount += 100.0;
            }
        }

        else if(insurancePlan instanceof GoldPlan){
            if (age > 55) {
                premiumAmount += 150.0;
            }
            if (smoking) {
                premiumAmount += 90.0;
            }
        }

        else if(insurancePlan instanceof SilverPlan){
            if (age > 55) {
                premiumAmount += 50.0;
            }
            if (smoking) {
                premiumAmount += 80.0;
            }
        }

        else if(insurancePlan instanceof BronzePlan){
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
