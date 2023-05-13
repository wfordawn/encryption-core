package com.dawn.encryption.market.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandlesDomain {

    private String time;

    private String open;

    private String high;

    private String low;

    private String close;

    private String vol;

    private String confirm;
}
