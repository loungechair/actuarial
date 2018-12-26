package com.kd.actuary.benefits;

import com.kd.actuary.timing.CalendarDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment
{
    private final CalendarDate paymentDate;
    private final double paymentAmount;
}
