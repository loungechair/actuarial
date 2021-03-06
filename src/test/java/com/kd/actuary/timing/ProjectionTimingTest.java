package com.kd.actuary.timing;

import org.junit.Test;

public class ProjectionTimingTest
{
    @Test
    public void test()
    {
        CalendarDate issueDate = CalendarDate.of(2018, 6, 10);
        TimePeriod timePeriod = TimePeriod.of(1, 0);
        ProjectionTiming pt = new ProjectionTiming(issueDate, timePeriod);
    }

    @Test
    public void test_getCm1()
    {
        CalendarDate issueDate = CalendarDate.of(2018, 1, 10);
        TimePeriod timePeriod = TimePeriod.of(0, 1);
        ProjectionTiming pt = new ProjectionTiming(issueDate, timePeriod);

        System.out.println(pt.getCm1());
        System.out.println(pt.getCm2());
    }
}
