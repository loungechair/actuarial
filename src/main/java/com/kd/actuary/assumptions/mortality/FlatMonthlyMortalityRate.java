package com.kd.actuary.assumptions.mortality;

public class FlatMonthlyMortalityRate implements MortalityAssumption {
    private double mortalityRate;

    public FlatMonthlyMortalityRate(double mortalityRate)
    {
        this.mortalityRate = mortalityRate;
    }

    @Override
    public double getPolicyMonthMortalityRate(int policyMonth)
    {
        return mortalityRate;
    }
}
