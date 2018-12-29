package com.kd.actuary.assumptions.interest;

public class ConstantInterestRate implements InterestAssumption
{
    double annualRate;
    double monthlyRate;
    double monthlyDiscountFactor;

    private ConstantInterestRate(double annualRate)
    {
        this.annualRate = annualRate;
        this.monthlyRate = InterestRateConverter.annualToMonthly(annualRate);
        this.monthlyDiscountFactor = 1.0/(1 + monthlyRate);
    }

    public static ConstantInterestRate fromMonthlyRate(double monthlyRate)
    {
        double annualRate = InterestRateConverter.monthlyToAnnual(monthlyRate);
        return new ConstantInterestRate(annualRate);
    }

    public static ConstantInterestRate fromAnnualRate(double annualRate)
    {
        return new ConstantInterestRate(annualRate);
    }

    @Override
    public double getMonthlyInterestRate(int policyMonth)
    {
        return monthlyRate;
    }

    @Override
    public double getMonthlyDiscountFactor(int policyMonth)
    {
        return monthlyDiscountFactor;
    }
}
