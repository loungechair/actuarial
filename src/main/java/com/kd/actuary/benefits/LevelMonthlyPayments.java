package com.kd.actuary.benefits;

import lombok.Builder;

@Builder
public class LevelMonthlyPayments implements PaymentSchedule
{
    @Builder.Default
    private final double monthStartPayment = 0.0;
    @Builder.Default
    private final double monthEndPayment = 0.0;

    @Override
    public double getMonthStartPayment(int policyMonth)
    {
        return monthStartPayment;
    }

    @Override
    public double getMonthEndPayment(int policyMonth)
    {
        return monthEndPayment;
    }
}
