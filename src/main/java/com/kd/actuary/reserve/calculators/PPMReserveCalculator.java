package com.kd.actuary.reserve.calculators;

import com.kd.actuary.assumptions.ProjectionAssumptions;
import com.kd.actuary.assumptions.StaticProjectionAssumptions;
import com.kd.actuary.assumptions.interest.InterestAssumption;
import com.kd.actuary.assumptions.mortality.MortalityAssumption;
import com.kd.actuary.timing.ProjectionTiming;

import java.util.Vector;

public class PPMReserveCalculator
{

    private Vector<Double> reserveFactors;
    private ProjectionTiming projectionTiming;
    private ProjectionAssumptions projectionAssumptions;

    private int projectionLength;

    public PPMReserveCalculator(ProjectionTiming projectionTiming,
                                ProjectionAssumptions projectionAssumptions)
    {
        this.projectionTiming = projectionTiming;
        this.projectionLength = projectionTiming.getProjectionLength();

        this.projectionAssumptions = projectionAssumptions;
    }

    @Deprecated
    public PPMReserveCalculator(ProjectionTiming projectionTiming,
                                InterestAssumption interestAssumption,
                                MortalityAssumption mortalityAssumption)
    {
        this.projectionTiming = projectionTiming;
        this.projectionLength = projectionTiming.getProjectionLength();

        this.projectionAssumptions = StaticProjectionAssumptions.builder()
                .interestAssumption(interestAssumption)
                .mortalityAssumption(mortalityAssumption)
                .build();
    }

    public void calculateReserveFactors()
    {
        reserveFactors = new Vector<>(projectionLength);
        for (int i = 0; i < projectionLength; ++i) {
            reserveFactors.add(i, 0.0);
        }

        int endTime = projectionLength - 1;
        reserveFactors.set(endTime, 0.0);

        for (int timePeriod = endTime - 1; timePeriod >= 0; --timePeriod) {
            double discountFactor = getMonthDiscountFactor(timePeriod);
            double expectedMonthEndPayment = getMonthlySurvivalRate(timePeriod) * getMonthEndPayment(timePeriod);
            double monthStartPayment = getMonthStartPayment(timePeriod);
            double reserve = (reserveFactors.get(timePeriod + 1) + expectedMonthEndPayment) * discountFactor
                    + monthStartPayment;
            reserveFactors.set(timePeriod, reserve);

            System.out.println(String.format("%d: %.2f", timePeriod, reserve));
        }
    }

    private double getMonthDiscountFactor(int policyMonth)
    {
        return projectionAssumptions.getMonthlyDiscountFactor(policyMonth);
    }

    private double getMonthStartPayment(int policyMonth)
    {
        return 0;
    }

    private double getMonthEndPayment(int policyMonth)
    {
        return 100;
    }

    private double getMonthlySurvivalRate(int policyMonth)
    {
        return projectionAssumptions.getMonthlySurvivalFactor(policyMonth);
    }
}
