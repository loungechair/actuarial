package com.kd.actuary.population;

import com.kd.actuary.timing.CalendarMonth;

import java.util.Date;

/*
 * Notes about projections:
 * 1. Data is stored for each half month
 * 2.
 */
public interface PopulationProjection {
    int getProjectionLengthInPolicyMonths();
    int getProjectionLengthInHalfMonths();

    PopulationData getPopulationAtCalendarMonthStart(CalendarMonth calendarMonth);
    PopulationData getPopulationAtCalendarMonthEnd(CalendarMonth calendarMonth);

    PopulationData getPopulationAtCalendarYearEnd(int calendarYear);
    PopulationData getPopulationAtCalendarYearStart(int calendarYear);

    PopulationData getPopulationAtPolicyMonthStart(int policyMonth);
    PopulationData getPopulationAtPolicyMOnthEnd(int policyMonth);

    PopulationData getPopulationAtPolicyYearStart(int policyYear);
    PopulationData getPopulationAtPolicyYearEnd(int policyYear);

    PopulationData getPopulationAtHalfMonthStart(int halfMonth);
    PopulationData getPopulationAtHalfMonthEnd(int halfMonth);
}
