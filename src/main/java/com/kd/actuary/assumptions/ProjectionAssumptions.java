package com.kd.actuary.assumptions;

import com.kd.actuary.assumptions.interest.InterestAssumption;
import com.kd.actuary.assumptions.mortality.MortalityAssumption;
import lombok.Builder;
import lombok.Data;

public interface ProjectionAssumptions
{
    // Interest related
    double getMonthlyDiscountFactor(int policyMonth);

    // Mortality related
    double getMonthlySurvivalFactor(int policyMonth);
}
