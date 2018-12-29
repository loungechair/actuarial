package com.kd.actuary.assumptions.interest;

public interface InterestAssumption
{
    double getMonthlyInterestRate(int policyMonth);
    double getMonthlyDiscountFactor(int policyMonth);
}
