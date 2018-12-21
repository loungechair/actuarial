package com.kd.actuary;

import com.kd.actuary.assumptions.mortality.FlatMonthlyMortalityRate;
import com.kd.actuary.assumptions.mortality.MortalityAssumption;
import com.kd.actuary.population.PolicyMonthPopulationProjection;
import com.kd.actuary.population.PolicyMonthProjectionEngine;

public class Application {
    public static void main(String[] args) {
        MortalityAssumption flatMortalityRate = new FlatMonthlyMortalityRate(0.01);
        PolicyMonthPopulationProjection x = PolicyMonthProjectionEngine.doSingleLifeProjection(
                12,
                flatMortalityRate);


    }
}
