package com.kd.actuary.timing;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

/*
 * 30/360 date format.
 */
@EqualsAndHashCode
public class CalendarDate
{
    private static final int BASE_YEAR = 1900;

    @Getter
    private final int year;
    @Getter
    private final int month;
    @Getter
    private final int day;

    public CalendarDate(int year, int month, int day)
    {
        Validate.isTrue(year >= BASE_YEAR,
                        String.format("year must be greater than %d, value is %d",
                                      BASE_YEAR,
                                      year));
        Validate.inclusiveBetween(1, 12, month,
                                  String.format("month must be between 1 and 12, value is %d",
                                                month));
        Validate.inclusiveBetween(1, 30, day,
                                  String.format("day must be between 1 and 30, value is %d",
                                                day));

        this.year = year;
        this.month = month;
        this.day = day;
    }

    static public CalendarDate of(int year, int month, int day) {
        return new CalendarDate(year, month, day);
    }

    static CalendarDate fromDayRef(int dayRef)
    {
        int adjustedDayRef = dayRef - 1;

        int day = adjustedDayRef % 30 + 1;
        int month = adjustedDayRef/30 % 12 + 1;
        int year = adjustedDayRef/360 + BASE_YEAR;

        return CalendarDate.of(year, month, day);
    }

    public String toString() {
        return String.format("%4d-%02d-%02d", year, month, day);
    }

    public CalendarDate atMonthEnd() {
        return CalendarDate.of(year, month, 30);
    }

    int dayRef()
    {
        return (year - BASE_YEAR) * 360
                + (month - 1) * 30
                + day;
    }

    public int monthRef()
    {
        return (year - BASE_YEAR) * 12
                + month;
    }

    public int daysBetween(CalendarDate d)
    {
        return d.dayRef() - dayRef();
    }

    public double monthsBetween(CalendarDate d)
    {
        return daysBetween(d) / 30.0;
    }

    public double yearsBetween(CalendarDate d)
    {
        return daysBetween(d) / 360.0;
    }

    public CalendarDate addDays(int days)
    {
        Validate.isTrue(days > 0, "years must be greater than 0: %s", days);
        return CalendarDate.fromDayRef(dayRef() + days);
    }

    public CalendarDate addMonths(int months)
    {
        Validate.isTrue(months > 0, "years must be greater than 0: %s", months);
        return CalendarDate.fromDayRef(dayRef() + 30 * months);
    }

    public CalendarDate addYears(int years)
    {
        Validate.isTrue(years > 0, "years must be greater than 0: %s", years);
        return CalendarDate.of(year + years, month, day);
    }
}
