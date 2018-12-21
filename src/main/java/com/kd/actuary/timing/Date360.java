package com.kd.actuary.timing;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

/*
 * 30/360 date format.
 */
@EqualsAndHashCode
public class Date360
{
    private static final int BASE_YEAR = 1900;

    @Getter
    private final int year;
    @Getter
    private final int month;
    @Getter
    private final int day;

    public Date360(int year, int month, int day)
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

    static public Date360 of(int year, int month, int day) {
        return new Date360(year, month, day);
    }

    static Date360 fromDayRef(int dayRef)
    {
        int adjustedDayRef = dayRef - 1;

        int day = adjustedDayRef % 30 + 1;
        int month = adjustedDayRef/30 % 12 + 1;
        int year = adjustedDayRef/360 + BASE_YEAR;

        return Date360.of(year, month, day);
    }

    public String toString() {
        return String.format("%4d-%02d-%02d", year, month, day);
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

    public int daysBetween(Date360 d)
    {
        return d.dayRef() - dayRef();
    }

    public double monthsBetween(Date360 d)
    {
        return daysBetween(d) / 30.0;
    }

    public double yearsBetween(Date360 d)
    {
        return daysBetween(d) / 360.0;
    }

    public Date360 addDays(int days)
    {
        Validate.isTrue(days > 0, "years must be greater than 0: %s", days);
        return Date360.fromDayRef(dayRef() + days);
    }

    public Date360 addMonths(int months)
    {
        Validate.isTrue(months > 0, "years must be greater than 0: %s", months);
        return Date360.fromDayRef(dayRef() + 30 * months);
    }

    public Date360 addYears(int years)
    {
        Validate.isTrue(years > 0, "years must be greater than 0: %s", years);
        return Date360.of(year + years, month, day);
    }
}
