package com.dawn.encryption.waring.domain;

import lombok.Data;

@Data
public class WaringDomain {

    private Long id;

    private String ccy;

    private String type;

    private String frequency;

    private String detail;

    private Integer status;

    private Long createdTime;
}
