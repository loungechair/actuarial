package com.kd.actuary.population;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PopulationData {
    private final double alive;
    private final double dead;
}
