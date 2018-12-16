package com.kd.actuary.population;

import org.apache.commons.lang3.Validate;

public class PolicyMonthPopulationProjection {
    private PopulationData[] populationData;

    public PolicyMonthPopulationProjection(PopulationData[] populationData)
    {
        this.populationData = populationData;
    }

    PopulationData getPopulationAtPolicyMonthStart(int policyMonth)
    {
        Validate.inclusiveBetween(policyMonth,1, populationData.length + 1);
        return populationData[policyMonth - 1];
    }

    PopulationData getPopulationAtPolicyMonthEnd(int policyMonth)
    {
        return null;
    }

    PopulationData getPopulationAtPolicyYearStart(int policyYear)
    {
        return null;
    }

    PopulationData getPopulationAtPolicyYearEnd(int policyYear)
    {
        return null;
    }
}
