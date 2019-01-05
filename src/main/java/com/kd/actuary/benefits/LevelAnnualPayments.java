package com.kd.actuary.benefits;

import lombok.Builder;

@Builder
public class LevelAnnualPayments implements PaymentSchedule
{
    private final double yearEndPayment;

    @Override
    public double getMonthStartPayment(int policyMonth)
    {
        return 0.0;
    }

    @Override
    public double getMonthEndPayment(int policyMonth)
    {
        return (policyMonth > 0 && policyMonth % 12 == 0) ? yearEndPayment
                                                          : 0.0;
    }
}
