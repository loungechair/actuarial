package com.kd.actuary.timing;

import lombok.Getter;

import java.util.Vector;

public class ProjectionTiming
{
    @Getter
    private Date360 issueDate;

    @Getter
    private int projectionMonths;

    Vector<ProjectionTimePoint> halfMonthEnds;

    public ProjectionTiming(Date360 issueDate, TimePeriod projectionLength)
    {
        this.issueDate = issueDate;
        this.projectionMonths = projectionLength.getPeriodLengthInMonths();

        initializeHalfMonthEnds();
    }

    private void initializeHalfMonthEnds() {
        halfMonthEnds = new Vector<>(2 * this.projectionMonths + 1);

        halfMonthEnds.add(0, new ProjectionTimePoint(issueDate, 0));

        Date360 currentDate = issueDate;

        for (int i = 0; i < projectionMonths; ++i) {
            halfMonthEnds.add(2 * i + 1,
                              new ProjectionTimePoint(currentDate.atMonthEnd(), 2 * i + 1));
            currentDate = currentDate.addMonths(1);
            halfMonthEnds.add(2 * i + 2,
                              new ProjectionTimePoint(currentDate, 2 * i + 2));
        }
    }

    public double getCm1() {
        return issueDate.getDay()/30.0;
    }

    public double getCm2() {
        return 1.0 - getCm1();
    }
}
