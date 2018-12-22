package com.kd.actuary.timing;

import lombok.Getter;

public class TimePeriod
{
    @Getter
    private final int years;

    @Getter
    private final int months;

    private TimePeriod(int years, int months)
    {
        this.years = years;
        this.months = months;
    }

    public static TimePeriod of(int years, int months)
    {
        return new TimePeriod(years, months);
    }

    int getPeriodLengthInMonths()
    {
        return years * 12 + months;
    }
}
