package com.kd.actuary.reserve.calculators;

import com.kd.actuary.assumptions.ProjectionAssumptions;
import com.kd.actuary.assumptions.StaticProjectionAssumptions;
import com.kd.actuary.assumptions.interest.ConstantInterestRate;
import com.kd.actuary.assumptions.interest.InterestAssumption;
import com.kd.actuary.assumptions.mortality.FlatMonthlyMortalityRate;
import com.kd.actuary.assumptions.mortality.MortalityAssumption;
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
        InterestAssumption interestRate = ConstantInterestRate.fromMonthlyRate(0.01);
        MortalityAssumption mortalityRate = new FlatMonthlyMortalityRate(0.02);

        ProjectionAssumptions projectionAssumptions = StaticProjectionAssumptions.builder()
                .interestAssumption(interestRate)
                .mortalityAssumption(mortalityRate)
                .build();

        PPMReserveCalculator res = new PPMReserveCalculator(timing, projectionAssumptions);
        res.calculateReserveFactors();
    }
}