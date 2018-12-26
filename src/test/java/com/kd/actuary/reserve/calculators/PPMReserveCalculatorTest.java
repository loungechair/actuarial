package com.kd.actuary.reserve.calculators;

import org.junit.Test;

import static org.junit.Assert.*;

public class PPMReserveCalculatorTest
{
    @Test
    public void test_calculateReserves(){
        PPMReserveCalculator res = new PPMReserveCalculator();
        res.calculateReserves();
    }
}