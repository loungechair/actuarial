package com.kd.actuary.benefits;

import com.kd.actuary.timing.Date360;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment
{
    private final Date360 paymentDate;
    private final double paymentAmount;
}
