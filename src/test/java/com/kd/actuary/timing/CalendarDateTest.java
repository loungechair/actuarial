package com.kd.actuary.timing;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalendarDateTest
{
    @Test
    public void test_constructor_success()
    {
        CalendarDate d = CalendarDate.of(1900, 1, 1);

        assertEquals(1900, d.getYear());
        assertEquals(1, d.getMonth());
        assertEquals(1, d.getDay());
        assertEquals(1, d.dayRef());
        assertEquals(1, d.monthRef());

        assertEquals(30, CalendarDate.of(1900, 1, 30).dayRef());
        assertEquals(1, CalendarDate.of(1900, 1, 30).monthRef());
    }

    @Test
    public void test_constructor_fromDayRef()
    {
        for (int month = 1; month <= 12; ++month) {
            for (int day = 1; day <= 30; ++day) {
                CalendarDate expected = CalendarDate.of(2018, month, day);
                CalendarDate test = CalendarDate.fromDayRef(expected.dayRef());

                assertEquals(expected, test);
            }
        }
    }

    @Test
    public void test_toString()
    {
        assertEquals("2018-07-06", CalendarDate.of(2018, 7, 6).toString());
    }

    @Test
    public void test_daysBetween()
    {
        assertEquals(20, CalendarDate.of(2010, 11, 1).daysBetween(CalendarDate.of(2010, 11, 21)));
        assertEquals(-20, CalendarDate.of(2010, 11, 21).daysBetween(CalendarDate.of(2010, 11, 1)));
    }

    @Test
    public void test_monthsBetween()
    {
        assertEquals(2.0 / 3, CalendarDate.of(2010, 11, 1).monthsBetween(CalendarDate.of(2010, 11, 21)), 1e-6);
        assertEquals(-2.0 / 3, CalendarDate.of(2010, 11, 21).monthsBetween(CalendarDate.of(2010, 11, 1)), 1e-6);
    }

    @Test
    public void test_yearsBetween()
    {
        CalendarDate d = CalendarDate.of(2018, 1, 10);

        assertEquals(1.0, d.yearsBetween(CalendarDate.of(2019, 1, 10)), 1e-6);
        assertEquals(0.5, d.yearsBetween(CalendarDate.of(2018, 7, 10)), 1e-6);
    }

    @Test
    public void test_addTime()
    {
        CalendarDate d = CalendarDate.of(2018, 1, 10);

        assertEquals(CalendarDate.of(2018, 1, 25), d.addDays(15));
        assertEquals(CalendarDate.of(2018, 2, 10), d.addDays(30));
        assertEquals(CalendarDate.of(2018, 2, 22), d.addDays(42));

        assertEquals(CalendarDate.of(2018, 2, 10), d.addMonths(1));
        assertEquals(CalendarDate.of(2018, 11, 10), d.addMonths(10));
        assertEquals(CalendarDate.of(2019, 1, 10), d.addMonths(12));
        assertEquals(CalendarDate.of(2019, 3, 10), d.addMonths(14));

        assertEquals(CalendarDate.of(2019, 1, 10), d.addYears(1));
        assertEquals(CalendarDate.of(2022, 1, 10), d.addYears(4));
    }
}