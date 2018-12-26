package com.kd.actuary.population;

import com.kd.actuary.timing.Date360;
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

    Date360 getIssueDate()
    {
        return projectionTiming.getIssueDate();
    }
}
