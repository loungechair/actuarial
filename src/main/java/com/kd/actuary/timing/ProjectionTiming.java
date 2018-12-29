package com.kd.actuary.timing;

import lombok.Builder;
import lombok.Getter;

import java.util.Vector;

public class ProjectionTiming
{
    @Getter
    private CalendarDate issueDate;

    @Getter
    private int projectionMonths;

    @Getter
    private int projectionHalfMonths;

    Vector<ProjectionTimePoint> halfMonthEnds;

    @Builder
    public ProjectionTiming(CalendarDate issueDate, TimePeriod projectionLength)
    {
        this.issueDate = issueDate;
        this.projectionMonths = projectionLength.getPeriodLengthInMonths();
        this.projectionHalfMonths = projectionMonths * 2 + 1;

        initializeHalfMonthEnds();
    }

    public int getProjectionLength()
    {
        return projectionMonths + 1;
    }

    private void initializeHalfMonthEnds() {
        halfMonthEnds = new Vector<>(projectionHalfMonths);

        CalendarDate currentDate = issueDate;

        halfMonthEnds.add(0, new ProjectionTimePoint(currentDate, 0));

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
