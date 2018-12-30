package com.kd.actuary.assumptions.mortality;

public class NoMortalityAssumption implements MortalityAssumption
{
    @Override
    public double getPolicyMonthMortalityRate(int policyMonth)
    {
        return 0.0;
    }
}
