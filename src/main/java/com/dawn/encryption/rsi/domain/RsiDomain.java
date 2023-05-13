package com.dawn.encryption.rsi.domain;

import lombok.Data;

@Data
public class RsiDomain {
    private Long id;

    private String ccy;

    private String period;

    private String rsi;

    private Integer rsiTime;

    private Integer createdTime;

    /**
     * K线状态
     * 0 代表 K 线未完结，1 代表 K 线已完结。
     */
    private String confirm;
}