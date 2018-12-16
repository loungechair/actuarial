package com.kd.actuary.assumptions.mortality;

public interface MortalityAssumption {
    double getPolicyMonthMortalityRate(int policyMonth);
}
