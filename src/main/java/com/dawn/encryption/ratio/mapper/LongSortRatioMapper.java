package com.dawn.encryption.ratio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dawn.encryption.ratio.entity.LongSortRatioEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface LongSortRatioMapper extends BaseMapper<LongSortRatioEntity> {

    @Select("select * from long_sort_ratio where ccy = #{ccy} and period = #{period} order by id desc limit 1")
    LongSortRatioEntity selectLastRatio(@Param("ccy") String ccy, @Param("period") String period);

}