package com.dawn.encryption.market.domain;

import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.enums.Period;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandlesQuery {

    private Ccy ccy;

    private Period period;
}
