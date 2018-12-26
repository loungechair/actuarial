package com.kd.actuary.population;

import com.kd.actuary.timing.CalendarDate;
import com.kd.actuary.timing.ProjectionTiming;

import java.util.Vector;

public class HalfMonthPopulationProjection
{
    Vector<PopulationData> populationData;
    ProjectionTiming projectionTiming;

    public HalfMonthPopulationProjection(Vector<PopulationData> populationData,
                                         ProjectionTiming projectionTiming)
    {
        this.populationData = populationData;
        this.projectionTiming = projectionTiming;
    }

    CalendarDate getIssueDate()
    {
        return projectionTiming.getIssueDate();
    }
}
