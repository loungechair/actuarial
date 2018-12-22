package com.kd.actuary.timing;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;


public class ProjectionTimePoint
{
    @Getter
    private final Date360 periodEnd;

    @Getter
    private final int halfMonth;

    @Getter
    private final int policyMonth;

    @Getter
    private final int policyYear;

    @Builder
    public ProjectionTimePoint(Date360 periodEnd, int halfMonth)
    {
        this.periodEnd = periodEnd;
        this.halfMonth = halfMonth;

        this.policyMonth = (halfMonth - 1)/2 + 1;
        this.policyYear = (policyMonth - 1)/12 + 1;
    }

    public static ProjectionTimePoint of(int year, int month, int day, int halfMonth)
    {
        return new ProjectionTimePoint(Date360.of(year, month, day), halfMonth);
    }

    public boolean isPolicyYearEnd()
    {
        return policyMonth % 12 == 0 && isPolicyMonthEnd();
    }

    public boolean isPolicyMonthEnd()
    {
        return halfMonth % 2 == 0;
    }

    public boolean isCalendarYearEnd()
    {
        return periodEnd.getMonth() == 12 && isCalendarMonthEnd();
    }

    public boolean isCalendarMonthEnd() {
        return halfMonth % 2 == 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(periodEnd.toString());

        if (isPolicyMonthEnd()) sb.append(",PME");
        if (isPolicyYearEnd()) sb.append(",PYE");
        if (isCalendarMonthEnd()) sb.append(",CME");
        if (isCalendarYearEnd()) sb.append(",CYE");

        return sb.toString();
    }
}
