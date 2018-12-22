package com.kd.actuary.timing;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimePeriodTest
{
    @Test
    public void test_constructor_success() {
        TimePeriod tp = TimePeriod.of(1, 2);

        assertEquals(1, tp.getYears());
        assertEquals(2, tp.getMonths());

        assertEquals(14, tp.getPeriodLengthInMonths());
    }
}
