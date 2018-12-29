package com.kd.actuary.assumptions.interest;

import org.apache.commons.lang3.Validate;

import javax.xml.validation.Validator;

public class InterestRateConverter
{
    static double annualToMonthly(double annualRate)
    {
        Validate.isTrue(annualRate > -1.0,
                        "annualRate must be greater than -1.0: %f", annualRate);

        return Math.pow(1.0 + annualRate, 1.0/12) - 1.0;
    }

    static double monthlyToAnnual(double monthlyRate)
    {
        Validate.isTrue(monthlyRate > -1.0,
                        "monthlyRate must be greater than -1.0: %f", monthlyRate);
        return Math.pow(1.0 + monthlyRate, 12.0) - 1.0;
    }
}
