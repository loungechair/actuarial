package com.kd.actuary.assumptions;

import com.kd.actuary.assumptions.interest.InterestAssumption;
import com.kd.actuary.assumptions.interest.NoInterestAssumption;
import com.kd.actuary.assumptions.mortality.MortalityAssumption;
import com.kd.actuary.assumptions.mortality.NoMortalityAssumption;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class StaticProjectionAssumptions implements ProjectionAssumptions
{
    @NonNull
    @Builder.Default
    private final MortalityAssumption mortalityAssumption = new NoMortalityAssumption();

    @NonNull
    @Builder.Default
    private final InterestAssumption interestAssumption = new NoInterestAssumption();

    @Override
    public double getMonthlyDiscountFactor(int policyMonth)
    {
        return interestAssumption.getMonthlyDiscountFactor(policyMonth);
    }

    @Override
    public double getMonthlySurvivalFactor(int policyMonth)
    {
        return 1.0 - mortalityAssumption.getPolicyMonthMortalityRate(policyMonth);
    }
}
