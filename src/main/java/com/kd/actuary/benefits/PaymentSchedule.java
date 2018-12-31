package com.kd.actuary.benefits;

public interface PaymentSchedule
{
    double getMonthStartPayment(int policyMonth);
    double getMonthEndPayment(int policyMonth);
}
