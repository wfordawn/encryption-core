package com.dawn.encryption.ratio.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("long_sort_ratio")
@Data
public class LongSortRatioEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String ccy;

    private String period;

    private Float ratio;

    private Long ratioTime;

    private Long createdTime;
}