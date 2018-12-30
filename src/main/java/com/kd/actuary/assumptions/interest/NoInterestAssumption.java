package com.kd.actuary.assumptions.interest;

public class NoInterestAssumption implements InterestAssumption
{
    @Override
    public double getMonthlyInterestRate(int policyMonth)
    {
        return 0.0;
    }

    @Override
    public double getMonthlyDiscountFactor(int policyMonth)
    {
        return 1.0;
    }
}
