package com.kd.actuary.reserve.calculators;

import com.kd.actuary.timing.Date360;
import com.kd.actuary.timing.ProjectionTiming;
import com.kd.actuary.timing.TimePeriod;
import org.junit.Test;

import static org.junit.Assert.*;

public class PPMReserveCalculatorTest
{
    @Test
    public void test_calculateReserves()
    {
        ProjectionTiming timing = ProjectionTiming.builder()
                .issueDate(Date360.of(2018, 1, 1))
                .projectionLength(TimePeriod.of(1, 6))
                .build();
        PPMReserveCalculator res = new PPMReserveCalculator(timing);
        res.calculateReserves();
    }
}