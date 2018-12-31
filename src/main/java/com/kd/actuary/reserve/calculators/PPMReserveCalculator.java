package com.kd.actuary.reserve.calculators;

import com.kd.actuary.assumptions.ProjectionAssumptions;
import com.kd.actuary.benefits.LevelMonthlyPayments;
import com.kd.actuary.timing.ProjectionTiming;
import lombok.Getter;

import java.util.Vector;

public class PPMReserveCalculator implements ReserveCalculator
{

    private Vector<Double> reserveFactors;
    private final ProjectionTiming projectionTiming;
    private final LevelMonthlyPayments paymentSchedule;
    private final ProjectionAssumptions projectionAssumptions;

    @Getter
    private int projectionLength;

    public PPMReserveCalculator(ProjectionTiming projectionTiming,
                                LevelMonthlyPayments paymentSchedule,
                                ProjectionAssumptions projectionAssumptions)
    {
        this.projectionTiming = projectionTiming;
        this.projectionLength = projectionTiming.getProjectionLength();
        this.paymentSchedule = paymentSchedule;
        this.projectionAssumptions = projectionAssumptions;
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
            double reserveFactor = (reserveFactors.get(timePeriod + 1) + expectedMonthEndPayment) * discountFactor
                    + monthStartPayment;
            reserveFactors.set(timePeriod, reserveFactor);

            System.out.println(String.format("%d: %.4f", timePeriod, reserveFactor));
        }
    }

    public double getPolicyMonthEndFactor(int policyMonth)
    {
        return reserveFactors.get(policyMonth);
    }

    private double getMonthDiscountFactor(int policyMonth)
    {
        return projectionAssumptions.getMonthlyDiscountFactor(policyMonth);
    }

    private double getMonthStartPayment(int policyMonth)
    {
        return paymentSchedule.getMonthStartPayment(policyMonth);
    }

    private double getMonthEndPayment(int policyMonth)
    {
        return paymentSchedule.getMonthEndPayment(policyMonth);
    }

    private double getMonthlySurvivalRate(int policyMonth)
    {
        return projectionAssumptions.getMonthlySurvivalFactor(policyMonth);
    }
}
