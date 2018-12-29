package com.kd.actuary.reserve.calculators;

import com.kd.actuary.timing.CalendarDate;
import com.kd.actuary.timing.ProjectionTiming;
import com.kd.actuary.timing.TimePeriod;
import org.junit.Test;

public class PPMReserveCalculatorTest
{
    @Test
    public void test_calculateReserves()
    {
        ProjectionTiming timing = ProjectionTiming.builder()
                .issueDate(CalendarDate.of(2018, 1, 15))
                .projectionLength(TimePeriod.of(1, 6))
                .build();
        PPMReserveCalculator res = new PPMReserveCalculator(timing);
        res.calculateReserveFactors();
    }
}