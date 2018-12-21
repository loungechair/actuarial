package com.kd.actuary.population;

import org.apache.commons.lang3.Validate;

public class PolicyMonthPopulationProjection {
    private PopulationData[] populationData;

    public PolicyMonthPopulationProjection(PopulationData[] populationData)
    {
        this.populationData = populationData;
    }

    public int getMaximumPolicyMonth()
    {
        return populationData.length;
    }

    public PopulationData getPopulationAtPolicyMonthStart(int policyMonth)
    {
        Validate.inclusiveBetween(policyMonth,1, populationData.length + 1);
        return populationData[policyMonth - 1];
    }

    public PopulationData getPopulationAtPolicyMonthEnd(int policyMonth)
    {
        Validate.inclusiveBetween(policyMonth, 0, populationData.length);
        return populationData[policyMonth];
    }

    public PopulationData getPopulationAtPolicyYearStart(int policyYear)
    {
        return null;
    }

    public PopulationData getPopulationAtPolicyYearEnd(int policyYear)
    {
        return null;
    }
}
