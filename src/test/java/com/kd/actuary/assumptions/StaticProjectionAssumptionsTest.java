package com.kd.actuary.assumptions;

import com.kd.actuary.assumptions.interest.ConstantInterestRate;
import com.kd.actuary.assumptions.interest.InterestAssumption;
import com.kd.actuary.assumptions.mortality.FlatMonthlyMortalityRate;
import com.kd.actuary.assumptions.mortality.MortalityAssumption;
import org.junit.Test;

import static org.junit.Assert.*;

public class StaticProjectionAssumptionsTest
{
    private static final double TOLERANCE = 1e-10;

    @Test
    public void test_noParameters() {
        StaticProjectionAssumptions spa = StaticProjectionAssumptions.builder().build();

        assertEquals(1.0, spa.getMonthlyDiscountFactor(1), TOLERANCE);
        assertEquals(1.0, spa.getMonthlySurvivalFactor(1), TOLERANCE);
    }

    @Test
    public void test_withParameters() {
        InterestAssumption interest = ConstantInterestRate.fromMonthlyRate(1.0/0.99 - 1);
        MortalityAssumption mortality = new FlatMonthlyMortalityRate(0.02);

        StaticProjectionAssumptions pa = StaticProjectionAssumptions.builder()
                .interestAssumption(interest)
                .mortalityAssumption(mortality)
                .build();

        assertEquals(0.99, pa.getMonthlyDiscountFactor(1), TOLERANCE);
        assertEquals(0.98, pa.getMonthlySurvivalFactor(1), TOLERANCE);
    }
}