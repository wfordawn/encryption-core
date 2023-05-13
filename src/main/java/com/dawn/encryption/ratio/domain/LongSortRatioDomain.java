package com.dawn.encryption.ratio.domain;

import lombok.Data;

@Data
public class LongSortRatioDomain {
    private Long id;

    private String ccy;

    private String period;

    private Float ratio;

    private Long ratioTime;

}