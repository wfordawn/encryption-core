package com.dawn.encryption.waring.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("waring")
public class WaringEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String ccy;

    private String type;

    private String frequency;

    private String detail;

    private Integer status;

    private Long createdTime;
}
