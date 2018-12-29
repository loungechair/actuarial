package com.kd.actuary.reserve.calculators;

import com.kd.actuary.timing.ProjectionTiming;

import java.util.Vector;

public class PPMReserveCalculator
{

    private Vector<Double> reserveFactors;
    private ProjectionTiming projectionTiming;

    private int projectionLength;

    public PPMReserveCalculator(ProjectionTiming projectionTiming)
    {
        this.projectionTiming = projectionTiming;
        this.projectionLength = projectionTiming.getProjectionLength();

        reserveFactors = new Vector<>(projectionLength);
        for (int i = 0; i < projectionLength; ++i) {
            reserveFactors.add(i, 0.0);
        }
    }

    public void calculateReserveFactors()
    {
        double discountFactor = 1.00;

        int endTime = projectionLength - 1;

        System.out.println("endTime = " + endTime);

        reserveFactors.set(endTime, 0.0);

        --endTime;

        for (int timePeriod = endTime; timePeriod >= 0; --timePeriod) {
            double expectedMonthEndPayment = getMonthMortalityRate(timePeriod) * getMonthEndPayment(timePeriod);
            double monthStartPayment = getMonthStartPayment(timePeriod);
            double reserve = (reserveFactors.get(timePeriod + 1) + expectedMonthEndPayment) * discountFactor
                    + monthStartPayment;
            reserveFactors.set(timePeriod, reserve);

            System.out.println(String.format("%d: %.2f", timePeriod, reserve));
        }
    }

    private double getMonthStartPayment(int month)
    {
        return 0;
    }

    private double getMonthEndPayment(int month)
    {
        return 100;
    }

    private double getMonthMortalityRate(int month)
    {
        return 1.00;
    }
}
