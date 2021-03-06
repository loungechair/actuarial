package com.kd.actuary.population;

import com.kd.actuary.assumptions.mortality.MortalityAssumption;

public class PolicyMonthProjectionEngine
{
    public static PolicyMonthPopulationProjection doSingleLifeProjection(int projectionMonths,
                                                                         MortalityAssumption mortalityAssumption)
    {
        PopulationData[] populationData = new PopulationData[projectionMonths + 1];
        populationData[0] = new PopulationData(1.0, 0.0);

        for (int i = 1; i < projectionMonths + 1; ++i) {
            double mortRate = mortalityAssumption.getPolicyMonthMortalityRate(i);
            double alive = populationData[i-1].getAlive() * (1 - mortRate);
            double dead = 1 - alive;

            populationData[i] = new PopulationData(alive, dead);
        }
        return new PolicyMonthPopulationProjection(populationData);
    }
}
