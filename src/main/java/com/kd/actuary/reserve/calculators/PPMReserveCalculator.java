package com.kd.actuary.reserve.calculators;

import com.kd.actuary.timing.ProjectionTiming;

import java.util.Vector;

public class PPMReserveCalculator
{

    private Vector<Double> reserves;
    private ProjectionTiming projectionTiming;

    private int projectionMonths;

    public PPMReserveCalculator(ProjectionTiming projectionTiming)
    {
        this.projectionTiming = projectionTiming;
        this.projectionMonths = projectionTiming.getProjectionMonths();

        reserves = new Vector<Double>(projectionMonths);
        for (int i = 0; i < projectionMonths; ++i) {
            reserves.add(i, 0.0);
        }
    }

    public void calculateReserves()
    {
        double discountFactor = 0.95;
        double periodEndPayment = 100;
        double periodStartPayment = 0;

        int endTime = projectionMonths - 1;

        reserves.set(endTime, 0.0);

        --endTime;

        for (int timePeriod = endTime; timePeriod >= 0; --timePeriod) {
            double reserve = (reserves.get(timePeriod + 1) + getMonthMortalityRate(timePeriod) * getMonthEndPayment(timePeriod))
                    * discountFactor
                    + getMonthStartPayment(timePeriod);
            reserves.set(timePeriod, reserve);
        }

        reserves.forEach(System.out::println);

    }

    private double getMonthStartPayment(int month)
    {
        return 50;
    }

    private double getMonthEndPayment(int month)
    {
        return 100;
    }

    private double getMonthMortalityRate(int month)
    {
        return 0.99;
    }
}
