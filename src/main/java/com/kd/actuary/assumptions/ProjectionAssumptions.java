package com.kd.actuary.assumptions;

import com.kd.actuary.assumptions.mortality.MortalityAssumption;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectionAssumptions
{
    private MortalityAssumption mortalityAssumption;
}
