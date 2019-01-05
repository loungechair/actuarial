package com.kd.actuary.reserve.calculators;

import com.kd.actuary.assumptions.ProjectionAssumptions;
import com.kd.actuary.assumptions.StaticProjectionAssumptions;
import com.kd.actuary.assumptions.interest.ConstantInterestRate;
import com.kd.actuary.assumptions.interest.InterestAssumption;
import com.kd.actuary.assumptions.mortality.FlatMonthlyMortalityRate;
import com.kd.actuary.assumptions.mortality.MortalityAssumption;
import com.kd.actuary.benefits.LevelAnnualPayments;
import com.kd.actuary.benefits.LevelMonthlyPayments;
import com.kd.actuary.benefits.PaymentSchedule;
import com.kd.actuary.timing.CalendarDate;
import com.kd.actuary.timing.ProjectionTiming;
import com.kd.actuary.timing.TimePeriod;
import org.junit.Test;

import static com.kd.actuary.testutils.TestConstants.TOLERANCE;
import static org.junit.Assert.*;

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

        LevelMonthlyPayments paymentSchedule = LevelMonthlyPayments.builder()
                .monthEndPayment(100.0)
                .build();

        PPMReserveCalculator res = new PPMReserveCalculator(timing, paymentSchedule, projectionAssumptions);
        res.calculateReserveFactors();

        double[] expectedValues = {
                1607.0303209275,
                1525.1006241367,
                1442.3516303781,
                1358.7751466819,
                1274.3628981487,
                1189.1065271302,
                1102.9975924015,
                1016.0275683255,
                928.1878440088,
                839.4697224489,
                749.8644196733,
                659.3630638701,
                567.9566945088,
                475.6362614539,
                382.3926240684,
                288.2165503091,
                193.0987158122,
                97.0297029703,
                0.0
        };

        for (int month = 0; month < res.getProjectionLength(); ++month) {
            assertEquals(expectedValues[month], res.getPolicyMonthEndFactor(month), TOLERANCE);
        }
    }

    @Test
    public void test_calculateReserves_annualPayments()
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

        PaymentSchedule paymentSchedule = LevelAnnualPayments.builder()
                .yearEndPayment(1200)
                .build();

        PPMReserveCalculator res = new PPMReserveCalculator(timing, paymentSchedule, projectionAssumptions);
        res.calculateReserveFactors();

        double[] expectedValues = {
                1033.3072167444,
                1043.6402889118,
                1054.0766918009,
                1064.6174587189,
                1075.2636333061,
                1086.0162696392,
                1096.8764323356,
                1107.8451966589,
                1118.9236486255,
                1130.1128851118,
                1141.4140139629,
                1152.8281541025,
                1164.3564356436,
                0.0000000000,
                0.0000000000,
                0.0000000000,
                0.0000000000,
                0.0000000000,
                0.0000000000
        };

        for (int month = 0; month < res.getProjectionLength(); ++month) {
            assertEquals(expectedValues[month], res.getPolicyMonthEndFactor(month), TOLERANCE);
        }
    }
}