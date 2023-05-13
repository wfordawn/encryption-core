package com.dawn.encryption.ratio.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dawn.encryption.adapter.ok.OkAdapter;
import com.dawn.encryption.ratio.domain.LongSortRatioDomain;
import com.dawn.encryption.enums.Ccy;
import com.dawn.encryption.enums.Period;
import com.dawn.encryption.ratio.entity.LongSortRatioEntity;
import com.dawn.encryption.ratio.mapper.LongSortRatioMapper;
import com.dawn.encryption.ratio.service.LongSortRatioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class LongSortRatioServiceImpl implements LongSortRatioService {

    @Autowired
    private OkAdapter okAdapter;

    @Autowired
    private LongSortRatioMapper mapper;


    @Override
    @Scheduled(cron = "0 0/7 * * * ?")
    public void save() {
        List<LongSortRatioDomain> longShortAccountRatio = okAdapter.getLongShortAccountRatio(Ccy.eth, null, null, Period.minute);
        if (CollUtil.isEmpty(longShortAccountRatio)) {
            return;
        }
        LongSortRatioDomain last = longShortAccountRatio.get(0);
        LongSortRatioEntity entity = mapper.selectLastRatio(Ccy.eth.name(), Period.minute.name());
        if (entity == null || Objects.equals(entity.getRatioTime(), last.getRatioTime())) {
            return;
        }
        LongSortRatioEntity newEntity = new LongSortRatioEntity();
        BeanUtil.copyProperties(last, newEntity);
        newEntity.setCreatedTime(DateUtil.currentSeconds());
        mapper.insert(newEntity);
    }

    @Override
    public Float findLastRatio(Ccy ccy) {
        LongSortRatioEntity entity = mapper.selectLastRatio(ccy.name(), Period.minute.name());
        if (entity == null) {
            throw new RuntimeException("findRatio id is null");
        }
        return entity.getRatio();
    }


    @Override
    public List<LongSortRatioDomain> findLast10(Ccy ccy) {
        Page<LongSortRatioEntity> page = new Page<>(1, 10);
        QueryWrapper<LongSortRatioEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ccy", Ccy.eth.name()).eq("period", Period.minute.name());
        queryWrapper.orderByDesc("id");
        Page<LongSortRatioEntity> result = mapper.selectPage(page, queryWrapper);
        List<LongSortRatioEntity> records = result.getRecords();

        return BeanUtil.copyToList(records, LongSortRatioDomain.class);
    }

}
